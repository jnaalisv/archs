package monolith.persistence.hibernate;

import monolith.application.ProductDetail;
import monolith.model.products.Product;
import monolith.model.products.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

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
    public void add(Product product) {
        getCurrentSession().save(product);
    }

    @Override
    public Optional<Product> findById(long productId) {
        return getCurrentSession()
                .byId(Product.class)
                .loadOptional(productId);
    }

    @Override
    public void update(Product product) {
        getCurrentSession().merge(product);
    }
}
