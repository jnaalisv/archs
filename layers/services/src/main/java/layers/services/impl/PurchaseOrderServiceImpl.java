package layers.services.impl;

import layers.model.orders.PurchaseOrderRepository;
import layers.model.products.Product;
import layers.services.ProductDetail;
import layers.services.PurchaseOrderDetail;
import layers.services.PurchaseOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderServiceImpl(final PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseOrderDetail> getPurchaseOrders() {
        logger.debug("getPurchaseOrders");
        return purchaseOrderRepository
                .getAll()
                .stream()
                .map(PurchaseOrderDetail::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(PurchaseOrderDetail purchaseOrder) {
        logger.debug("create");
        purchaseOrderRepository.create(PurchaseOrderAssembler.from(purchaseOrder));
    }
}
