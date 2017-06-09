package monolith.http;

import monolith.application.ProductDetail;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("classpath:datasource-test.properties")
@SpringBootTest(classes = {MonolithHttpApiApplication.class})
public class ProductControllerIntegrationTest extends AbstractMockMvcTest {

    @Sql({"classpath:clear-database.sql", "classpath:three-products.sql"})
    @Test
    public void givenThreeProductsInDatabase_whenGetProducts_thenReturnsThreeProductsFromDatabase() throws Exception {

        // when
        List<ProductDetail> productDetails = httpGet("/products")
                .acceptApplicationJson()
                .expect200()
                .responseBodyAsListOf(ProductDetail.class);

        // then
        assertThat(productDetails.size()).isEqualTo(3);
        ProductDetail firstProduct = productDetails.get(0);
        assertThat(firstProduct.name).isEqualTo("Jelly Beans");
        assertThat(firstProduct.version).isEqualTo(0);
        assertThat(firstProduct.id).isEqualTo(1);

        ProductDetail secondProduct = productDetails.get(1);
        assertThat(secondProduct.name).isEqualTo("Robusta");
        assertThat(secondProduct.version).isEqualTo(0);
        assertThat(secondProduct.id).isEqualTo(2);

        ProductDetail thirdProduct = productDetails.get(2);
        assertThat(thirdProduct.name).isEqualTo("Arabica");
        assertThat(thirdProduct.version).isEqualTo(0);
        assertThat(thirdProduct.id).isEqualTo(3);
    }

    @Sql({"classpath:clear-database.sql"})
    @Test
    public void givenEmptyDatabase_whenPostNewProduct_thenShouldPersistNewProduct() throws Exception {

        // when
        ProductDetail newProduct = new ProductDetail(0L, "Arabica");

        ProductDetail createdProduct = httpPost("/products")
                .acceptApplicationJson()
                .contentTypeApplicationJson()
                .content(newProduct)
                .expect201()
                .expectHeader("Location", "/products/1")
                .responseBodyAs(ProductDetail.class);


        // then
        assertThat(createdProduct.id).isNotZero();
        assertThat(createdProduct.name).isEqualTo(newProduct.name);
        assertThat(createdProduct.version).isEqualTo(0);

        ProductDetail productFromHttpGet = httpGet("/products/"+createdProduct.id)
                .acceptApplicationJson()
                .expect200()
                .responseBodyAs(ProductDetail.class);


        assertThat(productFromHttpGet.id).isEqualTo(createdProduct.id);
        assertThat(productFromHttpGet.name).isEqualTo(createdProduct.name);
        assertThat(productFromHttpGet.version).isEqualTo(0);
    }

    @Sql({"classpath:clear-database.sql", "classpath:products.sql"})
    @Test
    public void givenProductInDatabase_whenUpdatingProduct_thenVersionIncreased() throws Exception {

        // given
        ProductDetail productDetail = new ProductDetail(1L, "Arabica Beans", 0L);

        // when
        ProductDetail updatedProduct = httpPut("/products/"+productDetail.id)
                .acceptApplicationJson()
                .contentTypeApplicationJson()
                .content(productDetail)
                .expect200()
                .responseBodyAs(ProductDetail.class);

        // then
        assertThat(updatedProduct.id).isEqualTo(productDetail.id);
        assertThat(updatedProduct.name).isEqualTo(productDetail.name);
        assertThat(updatedProduct.version).isEqualTo(productDetail.version+1);
    }
}
