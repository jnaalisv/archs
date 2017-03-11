package monolith.model;

import monolith.persistence.HibernateProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final HibernateProductRepository hibernateProductRepository;

    public ProductService(final HibernateProductRepository hibernateProductRepository) {
        this.hibernateProductRepository = hibernateProductRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getProducts() {
        return hibernateProductRepository.getProducts();
    }

    @Transactional
    public void add(Product product) {
        hibernateProductRepository.add(product);
    }
}
