package layers.services;

import java.io.Serializable;
import java.util.List;

public interface ProductService {
    List<ProductDetail> getProducts();
    Serializable create(ProductDetail product);
    void update(long productId, ProductDetail product);
    ProductDetail getById(Serializable id);
}
