package layers.model.orders;

import java.util.Optional;

public interface PurchaseOrderRepository {
    void create(PurchaseOrder purchaseOrder);
    Optional<PurchaseOrder> readById(long id);
}
