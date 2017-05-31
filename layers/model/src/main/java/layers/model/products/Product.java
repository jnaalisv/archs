package layers.model.products;

import java.time.LocalDateTime;

public class Product {

    private long id;
    private String name;
    private LocalDateTime createTime;
    private long version;

    public Product(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Product(long id, String name, long version) {
        this(id, name);
        this.version = version;
    }

    public Product(long id, String name, long version, LocalDateTime createTime) {
        this(id, name, version);
        this.createTime = createTime;
    }

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
