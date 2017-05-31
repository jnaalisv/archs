package layers.services.impl;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import layers.services.ProductDetail;
import layers.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
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
        logger.debug("getProducts");
        return productRepository
                .getProducts()
                .stream()
                .map(ProductAssembler::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDetail create(ProductDetail product) {
        Product newProduct = new Product(product.name);

        Serializable id = productRepository.create(newProduct);

        return ProductAssembler.from(newProduct);
    }

    @Override
    @Transactional
    public ProductDetail update(long productId, ProductDetail productDetail) {

        Product product = ProductAssembler.forUpdate(productDetail);

        productRepository.update(product);

        return ProductAssembler.from(product);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetail findById(long productId) {

        Product product = productRepository
                .readById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found by id="+productId));

        return ProductAssembler.from(product);
    }
}
