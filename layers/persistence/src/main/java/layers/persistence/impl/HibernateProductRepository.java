package layers.persistence.impl;

import layers.persistence.model.Product;
import layers.persistence.model.ProductRepository;
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
