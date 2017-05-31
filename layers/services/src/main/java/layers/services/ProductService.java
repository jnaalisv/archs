package layers.services;

import java.util.List;

public interface ProductService {
    List<ProductDetail> getProducts();
    ProductDetail create(ProductDetail product);
    ProductDetail update(long productId, ProductDetail product);
    ProductDetail findById(long productId);
}
