package monolith.service.impl;

import monolith.service.ProductService;
import monolith.model.products.Product;
import monolith.model.products.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    @Transactional
    public void add(Product product) {
        productRepository.add(product);
    }
}
