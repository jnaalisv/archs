package layers.model.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaseOrder {

    private long id;
    private LocalDateTime createTime;
    private long version;
    private List<OrderLine> orderLines = new ArrayList<>();

    public PurchaseOrder(final long id, long version) {
        this.id = id;
        this.version = version;
    }

    public PurchaseOrder(final long id, LocalDateTime createTime, long version, final List<OrderLine> orderLines) {
        this.id = id;
        this.createTime = createTime;
        this.version = version;
        this.orderLines = orderLines;
        this.orderLines.forEach(orderLine -> orderLine.setPurchaseOrder(this));
    }

    public long getId() {
        return id;
    }

    public List<OrderLine> getOrderLines() {
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
