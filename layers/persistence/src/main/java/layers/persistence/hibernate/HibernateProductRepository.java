package layers.persistence.hibernate;

import layers.model.products.Product;
import layers.model.products.ProductRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class HibernateProductRepository implements ProductRepository {

    private final SessionFactory sessionFactory;

    public HibernateProductRepository(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public List<Product> getProducts() {
        return getCurrentSession()
                .createQuery("select p from Product p", Product.class)
                .list();
    }

    public void add(Product product) {
        getCurrentSession().save(product);
    }
}
