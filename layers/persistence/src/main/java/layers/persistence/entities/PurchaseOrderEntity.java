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
    private long id;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Version
    private long version;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderLineEntity> orderLines = new ArrayList<>();

    public PurchaseOrderEntity() { /* hibernate*/}

    public PurchaseOrderEntity(final long id, long version) {
        this.id = id;
        this.version = version;
    }

    public PurchaseOrderEntity(final long id, long version, final List<OrderLineEntity> orderLines) {
        this.id = id;
        this.version = version;
        this.orderLines = orderLines;

        this.orderLines.forEach(orderLine -> orderLine.setPurchaseOrder(this));
    }

    public PurchaseOrderEntity(final long id, final List<OrderLineEntity> orderLines) {
        this.id = id;
        this.orderLines = orderLines;
        this.orderLines.forEach(orderLine -> orderLine.setPurchaseOrder(this));
    }

    public PurchaseOrderEntity(final long id, final OrderLineEntity...orderLines) {
        this(id, Arrays.asList(orderLines));
    }

    public long getId() {
        return id;
    }

    public List<OrderLineEntity> getOrderLines() {
        return orderLines;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
