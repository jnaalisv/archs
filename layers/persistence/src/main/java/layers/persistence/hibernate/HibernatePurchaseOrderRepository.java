package layers.persistence.hibernate;

import layers.model.orders.OrderLine;
import layers.model.orders.PurchaseOrder;
import layers.model.orders.PurchaseOrderRepository;
import layers.persistence.entities.OrderLineEntity;
import layers.persistence.entities.PurchaseOrderEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
class HibernatePurchaseOrderRepository implements PurchaseOrderRepository {

    private final SessionFactory sessionFactory;

    public HibernatePurchaseOrderRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return getCurrentSession()
                .createQuery("select p from PurchaseOrderEntity p", PurchaseOrderEntity.class)
                .stream()
                .map(purchaseOrderEntity ->
                        new PurchaseOrder(
                                purchaseOrderEntity.id,
                                purchaseOrderEntity.createTime,
                                purchaseOrderEntity.version,
                                purchaseOrderEntity
                                        .orderLines
                                        .stream()
                                        .map(orderLineEntity ->
                                                new OrderLine(
                                                        orderLineEntity.id,
                                                        orderLineEntity.productId,
                                                        orderLineEntity.amount))
                                        .collect(Collectors.toList())

                        ))
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity(
                purchaseOrder.getId(),
                purchaseOrder.getVersion(),
                purchaseOrder.getOrderLines()
                    .stream()
                    .map(orderLine -> new OrderLineEntity(
                            orderLine.getId(),
                            orderLine.getProductId(),
                            orderLine.getAmount()))
                    .collect(Collectors.toList())
                );

        getCurrentSession().save(purchaseOrderEntity);
        getCurrentSession().flush();


        return new PurchaseOrder(
                purchaseOrderEntity.id,
                purchaseOrderEntity.createTime,
                purchaseOrderEntity.version,
                purchaseOrderEntity
                        .orderLines
                        .stream()
                        .map(orderLineEntity ->
                                new OrderLine(
                                        orderLineEntity.id,
                                        orderLineEntity.productId,
                                        orderLineEntity.amount))
                        .collect(Collectors.toList())

        );
    }

    @Override
    public Optional<PurchaseOrder> readById(long id) {
        return getCurrentSession()
                .createQuery("select p from PurchaseOrderEntity p where p.id = :id", PurchaseOrderEntity.class)
                .setParameter("id", id)
                .uniqueResultOptional()
                .map(purchaseOrderEntity ->
                        new PurchaseOrder(
                            purchaseOrderEntity.id,
                            purchaseOrderEntity.createTime,
                            purchaseOrderEntity.version,
                            purchaseOrderEntity
                                    .orderLines
                                    .stream()
                                    .map(orderLineEntity ->
                                            new OrderLine(
                                                orderLineEntity.id,
                                                orderLineEntity.productId,
                                                orderLineEntity.amount))
                                .collect(Collectors.toList())

                    )
                );
    }

    @Override
    public void update(PurchaseOrder purchaseOrder) {
        PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity(
                purchaseOrder.getId(),
                purchaseOrder.getVersion(),
                purchaseOrder.getOrderLines()
                        .stream()
                        .map(orderLine -> new OrderLineEntity(
                                orderLine.getId(),
                                orderLine.getProductId(),
                                orderLine.getAmount()))
                        .collect(Collectors.toList())
        );

        getCurrentSession().merge(purchaseOrderEntity);
        getCurrentSession().flush();

//        return new PurchaseOrder(
//                purchaseOrderEntity.getId(),
//                purchaseOrderEntity.getCreateTime(),
//                purchaseOrderEntity.getVersion(),
//                purchaseOrderEntity
//                        .getOrderLines()
//                        .stream()
//                        .map(orderLineEntity ->
//                                new OrderLine(
//                                        orderLineEntity.getId(),
//                                        orderLineEntity.getProductId(),
//                                        orderLineEntity.getAmount()))
//                        .collect(Collectors.toList())
//
//        );
    }


}
