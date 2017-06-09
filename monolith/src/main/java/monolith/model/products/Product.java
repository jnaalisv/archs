package monolith.model.products;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Version
    private Long version;

    public Product(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(String name) {
        this.id = id;
        this.name = name;
    }

    public Product() { /* hibernate */}

    public Product(long id, String name, Long version) {
        this(id, name);
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }
}
