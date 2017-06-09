package monolith.model.products;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getProducts();

    void add(Product product);

    Optional<Product> findById(long productId);

    void update(Product product);
}
