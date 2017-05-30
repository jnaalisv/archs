
package layers.model.orders;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "product_id")
    private long productId;

    private BigDecimal amount;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    public OrderLine() { /* hibernate */}

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
