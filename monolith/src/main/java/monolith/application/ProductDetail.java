package monolith.application;

public class ProductDetail {
    public long id;
    public String name;
    public Long version;

    public ProductDetail(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductDetail() {

    }

    public ProductDetail(long id, String name, Long version) {
        this(id, name);
        this.version = version;
    }
}
