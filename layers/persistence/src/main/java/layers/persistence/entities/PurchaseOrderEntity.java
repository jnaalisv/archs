package layers.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="PurchaseOrder")
public class PurchaseOrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public long id;

    @Column(insertable = false, updatable = false)
    public LocalDateTime createTime;

    @Version
    public long version;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderLineEntity> orderLines = new ArrayList<>();

    public PurchaseOrderEntity() { /* hibernate*/}

    public PurchaseOrderEntity(final long id, long version, final List<OrderLineEntity> orderLines) {
        this.id = id;
        this.version = version;
        this.orderLines = orderLines;
        this.orderLines.forEach(orderLine -> orderLine.purchaseOrder = this);
    }
}
