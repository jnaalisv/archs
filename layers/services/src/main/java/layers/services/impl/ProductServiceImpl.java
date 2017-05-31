package layers.services.impl;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import layers.services.ProductDetail;
import layers.services.ProductService;
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

    @Override
    @Transactional(readOnly = true)
    public List<ProductDetail> getProducts() {
        logger.debug("getProducts");
        return productRepository
                .getProducts()
                .stream()
                .map(product -> new ProductDetail(product.getId(), product.getName(), product.getVersion()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ProductDetail product) {
        productRepository.add(new Product(product.id, product.name));
    }

    @Override
    @Transactional
    public ProductDetail update(long productId, ProductDetail productDetail) {

        Product productToUpdate = ProductAssembler.forUpdate(productDetail);

        Product updatedProduct = productRepository.update(productToUpdate);

        return ProductAssembler.from(updatedProduct);
    }
}
