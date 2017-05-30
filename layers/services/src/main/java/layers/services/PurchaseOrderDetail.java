package layers.services;

import layers.model.orders.OrderLine;
import layers.model.orders.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDetail {
    public long id;
    public LocalDateTime createTime;
    public long version;
    public List<OrderLineDetail> orderLines = new ArrayList<>();

    public static PurchaseOrderDetail from(PurchaseOrder purchaseOrder) {
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();

        return purchaseOrderDetail;
    }
}
