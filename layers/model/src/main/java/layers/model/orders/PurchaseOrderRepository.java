package layers.model.orders;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository {
    List<PurchaseOrder> getAll();
    PurchaseOrder create(PurchaseOrder purchaseOrder);
    Optional<PurchaseOrder> readById(long id);

    void update(PurchaseOrder purchaseOrder);
}
