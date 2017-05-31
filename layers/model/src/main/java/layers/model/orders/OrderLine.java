
package layers.model.orders;

import java.math.BigDecimal;

public class OrderLine {

    private long id;
    private long productId;
    private BigDecimal amount;
    private PurchaseOrder purchaseOrder;

    public OrderLine(long productId, BigDecimal amount, PurchaseOrder purchaseOrder) {
        this.productId = productId;
        this.amount = amount;
        this.purchaseOrder = purchaseOrder;
    }

    public OrderLine(long id, long productId, BigDecimal amount) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
    }

    public OrderLine(long id, long productId, BigDecimal amount, PurchaseOrder purchaseOrder) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.purchaseOrder = purchaseOrder;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
