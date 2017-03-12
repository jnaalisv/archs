package layers.application.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {ApplicationConfiguration.class})
@RunWith(SpringRunner.class)
public class ProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void shouldAddProduct() {
        ProductDetail productDetail = new ProductDetail(0, "Cool Beans");

        productService.add(productDetail);
    }

    @Test
    public void shouldGetProducts() {
        // depends on previous test
        assertThat(productService.getProducts().size()).isEqualTo(1);
    }
}
