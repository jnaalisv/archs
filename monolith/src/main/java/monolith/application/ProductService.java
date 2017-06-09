package monolith.application;

import java.util.List;

public interface ProductService {

    List<ProductDetail> getProducts();

    ProductDetail create(ProductDetail product);

    ProductDetail findById(long productId);

    ProductDetail update(long productId, ProductDetail productDetail);
}
