package layers.model.orders;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepository {
    List<PurchaseOrder> getAll();
    void create(PurchaseOrder purchaseOrder);
    Optional<PurchaseOrder> readById(long id);
}
