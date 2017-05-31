package layers.persistence.hibernate;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import layers.persistence.entities.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Repository
class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    public HibernateProductRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Product> getProducts() {
        return getCurrentSession()
                .createQuery("select p from ProductEntity p", ProductEntity.class)
                .list()
                .stream()
                .map(product -> {
                    return new Product(
                            product.getId(),
                            product.getName(),
                            product.getVersion(),
                            product.getCreateTime());
                })
                .collect(Collectors.toList());
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
