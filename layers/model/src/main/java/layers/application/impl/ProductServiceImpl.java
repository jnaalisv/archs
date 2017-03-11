package layers.application.impl;

import layers.application.api.ProductDetail;
import layers.application.api.ProductService;
import layers.persistence.model.Product;
import layers.persistence.model.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDetail> getProducts() {
        return productRepository
                .getProducts()
                .stream()
                .map(product -> new ProductDetail(product.getId(), product.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void add(ProductDetail product) {
        productRepository.add(
                new Product(product.id, product.name)
        );
    }
}
