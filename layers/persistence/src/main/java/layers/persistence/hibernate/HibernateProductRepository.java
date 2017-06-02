package layers.persistence.hibernate;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import layers.persistence.entities.ProductEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
class HibernateProductRepository implements ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(HibernateProductRepository.class);

    private static final Function<ProductEntity, Product> entityToModel =
            productEntity ->
                    new Product(
                            productEntity.id,
                            productEntity.name,
                            productEntity.version,
                            productEntity.createTime);

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
                .map(entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public Serializable create(Product newProduct) {
        ProductEntity product = new ProductEntity(newProduct.getName());
        return getCurrentSession().save(product);
    }

    @Override
    public void update(Product productToUpdate) {
        ProductEntity product = new ProductEntity(productToUpdate.getId(), productToUpdate.getName(), productToUpdate.getVersion());
        getCurrentSession().merge(product);
        getCurrentSession().flush();
    }

    @Override
    public Optional<Product> findById(Serializable id) {
        return getCurrentSession()
                .byId(ProductEntity.class)
                .loadOptional(id)
                .map(entityToModel);
    }
}
