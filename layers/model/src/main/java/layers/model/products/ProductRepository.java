package layers.model.products;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
    void add(Product product);
    Product update(Product productToUpdate);
}
