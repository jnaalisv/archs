package layers.model.products;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getProducts();
    Serializable create(Product product);
    void update(Product product);
    Optional<Product> readById(Serializable productId);
}
