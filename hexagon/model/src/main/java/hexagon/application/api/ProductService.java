package hexagon.application.api;

import java.util.List;

public interface ProductService {
    List<ProductDetail> getProducts();
    void add(ProductDetail product);
}
