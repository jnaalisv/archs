package layers.persistence.hibernate;

import layers.model.orders.PurchaseOrder;
import layers.model.orders.PurchaseOrderRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public void create(PurchaseOrder purchaseOrder) {
        getCurrentSession().save(purchaseOrder);
    }

    @Override
    public Optional<PurchaseOrder> readById(long id) {
        return getCurrentSession()
                .createQuery("select p from PurchaseOrder p", PurchaseOrder.class)
                .uniqueResultOptional();
    }
}
