package monolith.application.impl;

import monolith.application.ProductDetail;
import monolith.application.ProductService;
import monolith.model.products.Product;
import monolith.model.products.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDetail> getProducts() {

        return productRepository
                .getProducts()
                .stream()
                .map(product -> new ProductDetail(product.getId(), product.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDetail create(ProductDetail productDetail) {

        Product product = new Product(productDetail.name);

        productRepository.add(product);

        return new ProductDetail(product.getId(), product.getName());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetail findById(long productId) {
        return productRepository
                .findById(productId)
                .map(product -> new ProductDetail(product.getId(), product.getName()))
                .orElseThrow(() -> new RuntimeException("Product not found by id="+productId));
    }

    @Override
    @Transactional
    public ProductDetail update(long productId, ProductDetail productDetail) {

        Product product = new Product(productDetail.id, productDetail.name);

        productRepository.update(product);

        return new ProductDetail(product.getId(), product.getName());
    }
}
