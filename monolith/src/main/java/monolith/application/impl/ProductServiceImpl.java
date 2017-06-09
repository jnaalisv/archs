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
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDetail> getProducts() {
        logger.debug("getProducts");
        return productRepository
                .getProducts()
                .stream()
                .map(product -> new ProductDetail(product.getId(), product.getName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void add(ProductDetail productDetail) {
        logger.debug("add");

        Product product = new Product(productDetail.id, productDetail.name);

        productRepository.add(product);
    }
}
