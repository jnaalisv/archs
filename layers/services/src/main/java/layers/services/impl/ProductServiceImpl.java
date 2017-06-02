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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private static final Function<ProductDetail, Product> fromDetail =
            productDetail -> new Product(productDetail.id, productDetail.name, productDetail.version);

    private static final Function<Product, ProductDetail> toDetail =
            product -> new ProductDetail(product.getId(), product.getName(), product.getVersion(), product.getCreateTime());

    @Override
    @Transactional(readOnly = true)
    public List<ProductDetail> getProducts() {
        return productRepository
                .getProducts()
                .stream()
                .map(toDetail)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Serializable create(ProductDetail product) {
        return productRepository.create(fromDetail.apply(product));
    }

    @Override
    @Transactional
    public void update(long productId, ProductDetail productDetail) {

        Product productToUpdate = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found by id="+productId));

        productToUpdate.setName(productDetail.name);
        productToUpdate.setVersion(productDetail.version);
        productToUpdate.setName(productDetail.name);

        productRepository.update(productToUpdate);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDetail getById(Serializable productId) {
        return productRepository
                .findById(productId)
                .map(toDetail)
                .orElseThrow(() -> new RuntimeException("Product not found by id="+productId));
    }
}
