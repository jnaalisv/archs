package layers.services;

import java.util.List;

public interface PurchaseOrderService {

    List<PurchaseOrderDetail> getPurchaseOrders();

    void create(PurchaseOrderDetail purchaseOrder);
}
