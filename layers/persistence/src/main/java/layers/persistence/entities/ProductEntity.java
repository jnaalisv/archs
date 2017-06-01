package layers.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
@Table(name="Product")
public class ProductEntity {

    @Id
    @GeneratedValue
    public long id;

    public String name;

    @Column(insertable = false, updatable = false)
    public LocalDateTime createTime;

    @Version
    public long version;

    public ProductEntity(String name) {
        this.name = name;
    }

    public ProductEntity(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public ProductEntity() { /* hibernate */}
}
