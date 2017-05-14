package monolith.service;

import monolith.model.products.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    void add(Product product);
}
