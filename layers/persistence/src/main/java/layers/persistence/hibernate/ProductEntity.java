package layers.persistence.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Version
    private long version;

    public ProductEntity(String name) {
        this.name = name;
    }

    public ProductEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductEntity(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public ProductEntity() {}

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public long getVersion() {
        return version;
    }
}
