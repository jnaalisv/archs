package layers.persistence.model;

import layers.model.Product;
import layers.model.ProductRepository;
import layers.persistence.config.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:datasource-test.properties")
@ContextConfiguration(classes = {HibernateConfiguration.class})
@RunWith(SpringRunner.class)
public class ProductRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductRepository productRepository;

    @Sql({"classpath:clear-database.sql"})
    @Test
    public void shouldAddProduct() {

        productRepository.add(new Product(0, "Cool Beans"));
        productRepository.add(new Product(0, "Arabica Beans"));

        sessionFactory.getCurrentSession().flush();

        assertThat(countRowsInTableWhere("product", "name = 'Cool Beans'")).isEqualTo(1);
        assertThat(countRowsInTableWhere("product", "name like '%Beans'")).isEqualTo(2);
        assertThat(countRowsInTableWhere("product", null)).isEqualTo(2);
    }

    @Sql(scripts = {
            "classpath:clear-database.sql"
    }, statements = {
        "insert into product(id, name) values (1, 'Cool Beans'),(2, 'Java Beans');"
    })
    @Test
    public void shouldGetAllProducts() {
        assertThat(productRepository.getProducts().size()).isEqualTo(2);
    }
}
