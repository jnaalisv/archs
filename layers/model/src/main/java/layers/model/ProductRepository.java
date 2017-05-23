package layers.model;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
    void add(Product product);
}
