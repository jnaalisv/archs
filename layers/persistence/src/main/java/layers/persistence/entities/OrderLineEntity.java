
package layers.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="OrderLine")
public class OrderLineEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "product_id")
    private long productId;

    private BigDecimal amount;

    @ManyToOne
    private PurchaseOrderEntity purchaseOrder;

    public OrderLineEntity() { /* hibernate */}

    public OrderLineEntity(long productId, BigDecimal amount, PurchaseOrderEntity purchaseOrder) {
        this.productId = productId;
        this.amount = amount;
        this.purchaseOrder = purchaseOrder;
    }

    public OrderLineEntity(long id, long productId, BigDecimal amount) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
    }

    public OrderLineEntity(long id, long productId, BigDecimal amount, PurchaseOrderEntity purchaseOrder) {
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

    public void setPurchaseOrder(PurchaseOrderEntity purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }
}
