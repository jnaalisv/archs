package layers.persistence.hibernate;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

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
                .createQuery("select p from Product p", Product.class)
                .list();
    }

    @Override
    public Serializable create(Product product) {
        Serializable id = getCurrentSession().save(product);
        getCurrentSession().flush();
        return id;
    }

    @Override
    public void update(Product product) {
        getCurrentSession().update(product);
        getCurrentSession().flush();
    }

    @Override
    public Optional<Product> readById(Serializable productId) {
        return getCurrentSession()
                .byId(Product.class)
                .loadOptional(productId);
    }
}
