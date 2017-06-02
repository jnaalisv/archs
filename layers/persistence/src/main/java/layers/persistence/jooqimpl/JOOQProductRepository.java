package layers.persistence.jooqimpl;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import layers.persistence.entities.ProductEntity;
import layers.persistence.jooq.Tables;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
class JOOQProductRepository implements ProductRepository {

    private final DefaultDSLContext create;

    public JOOQProductRepository(final DefaultDSLContext dsl) {
        this.create = dsl;
    }

    @Override
    public List<Product> getProducts() {
        create.selectFrom(Tables.PRODUCT)
                .fetch();

//        return getCurrentSession()
//                .createQuery("select p from ProductEntity p", ProductEntity.class)
//                .list()
//                .stream()
//                .map(product -> {
//                    return new Product(
//                            product.getId(),
//                            product.getName(),
//                            product.getVersion(),
//                            product.getCreateTime());
//                })
//                .collect(Collectors.toList());
    }

    @Override
    public Product create(Product newProduct) {
        ProductEntity product = new ProductEntity(newProduct.getName());

        Serializable id = getCurrentSession().save(product);
        return new Product(
                product.getId(),
                product.getName(),
                product.getVersion(),
                product.getCreateTime());
    }

    @Override
    public Product update(Product productToUpdate) {

        ProductEntity product = new ProductEntity(productToUpdate.getId(), productToUpdate.getName(), productToUpdate.getVersion());
        getCurrentSession().update(product);
        getCurrentSession().flush();

        return new Product(
                product.getId(),
                product.getName(),
                product.getVersion(),
                product.getCreateTime());
    }
}
