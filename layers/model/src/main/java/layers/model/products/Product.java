package layers.model.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    @Version
    private Long version;

    public Product(String name) {
        this.name = name;
    }

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public Product() {}

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
