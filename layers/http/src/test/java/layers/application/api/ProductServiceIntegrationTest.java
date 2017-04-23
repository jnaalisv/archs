package layers.application.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:datasource-test.properties")
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@RunWith(SpringRunner.class)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Sql({"classpath:clear-database.sql"})
    @Test
    public void shouldAddProduct() {
        ProductDetail productDetail = new ProductDetail(0, "Cool Beans");

        productService.add(productDetail);
    }

    @Sql({"classpath:clear-database.sql", "classpath:products.sql"})
    @Test
    public void shouldGetProducts() {
        assertThat(productService.getProducts().size()).isEqualTo(1);
    }
}
