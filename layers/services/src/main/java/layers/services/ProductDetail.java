package layers.services;

public class ProductDetail {
    public long id;
    public String name;
    public long version;

    public ProductDetail(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public ProductDetail() {

    }
}
