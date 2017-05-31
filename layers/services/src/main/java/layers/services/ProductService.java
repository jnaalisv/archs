package layers.services;

import java.util.List;

public interface ProductService {
    List<ProductDetail> getProducts();
    void create(ProductDetail product);
    ProductDetail update(long productId, ProductDetail product);
}
