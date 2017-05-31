package layers.services;

import java.time.LocalDateTime;

public class ProductDetail {
    public Long id;
    public String name;
    public Long version;
    public LocalDateTime createTime;

    public ProductDetail(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public ProductDetail(long id, String name, long version, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.createTime = createTime;
    }

    public ProductDetail() {

    }

    public ProductDetail(String name) {
        this.name = name;
    }
}
