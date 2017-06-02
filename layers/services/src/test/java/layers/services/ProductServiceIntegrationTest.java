package layers.services;

import layers.model.products.Product;
import layers.persistence.HibernateConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:datasource-test.properties")
@ContextConfiguration(classes = {HibernateConfiguration.class, ServicesConfiguration.class})
@RunWith(SpringRunner.class)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Sql({"classpath:clear-database.sql"})
    @Test
    public void shouldAddProduct() {
        ProductDetail productDetail = new ProductDetail(0, "Cool Beans", 0L);

        productService.create(productDetail);
    }

    @Sql({"classpath:clear-database.sql", "classpath:products.sql"})
    @Test
    public void shouldGetProducts() {
        assertThat(productService.getProducts().size()).isEqualTo(1);
    }


    @Sql(scripts = {
            "classpath:clear-database.sql"
    }, statements = {
            "insert into product(id, name) values (1, 'Cool Beans'),(2, 'Java Beans');"
    })
    @Test
    public void shouldUpdateProduct() {
        ProductDetail product = productService.getProducts().get(0);
        product.name = "New Hotness";

        productService.update(product.id, product);

        ProductDetail updatedProduct = productService.getById(product.id);
        assertThat(updatedProduct.version).isEqualTo(1);
        assertThat(updatedProduct.name).isEqualTo(product.name);
    }
}
