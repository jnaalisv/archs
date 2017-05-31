package layers.model.products;

import java.util.List;

public interface ProductRepository {
    List<Product> getProducts();
    Product create(Product product);
    Product update(Product productToUpdate);
}
