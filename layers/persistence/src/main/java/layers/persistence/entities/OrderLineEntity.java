
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
    public long id;

    @Column(name = "product_id")
    public long productId;

    public BigDecimal amount;

    @ManyToOne
    PurchaseOrderEntity purchaseOrder;

    public OrderLineEntity() { /* hibernate */}

    public OrderLineEntity(long id, long productId, BigDecimal amount) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
    }
}
