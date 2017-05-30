package layers.services.impl;

import layers.model.orders.OrderLine;
import layers.model.orders.PurchaseOrder;
import layers.services.PurchaseOrderDetail;

class PurchaseOrderAssembler {

    static PurchaseOrder from(PurchaseOrderDetail purchaseOrderDetail) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDetail.id, purchaseOrderDetail.version);
        purchaseOrderDetail
                .orderLines
                .forEach(
                        orderLineDetail -> {
                            OrderLine orderLine = new OrderLine(orderLineDetail.id, orderLineDetail.productId, orderLineDetail.amount, purchaseOrder);
                            purchaseOrder.getOrderLines().add(orderLine);
                        }
                );

        return purchaseOrder;
    }
}
