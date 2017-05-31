package layers.http;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import layers.ApplicationConfig;
import layers.config.SerializationConfiguration;
import layers.services.ProductDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("classpath:datasource-test.properties")
@AutoConfigureMockMvc
@SpringBootTest(classes = {ApplicationConfig.class})
@RunWith(SpringRunner.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private final ObjectMapper objectMapper = SerializationConfiguration.objectMapper();

    @Sql({"classpath:clear-database.sql", "classpath:products.sql"})
    @Test
    public void getProductsShouldReturnProducts() throws Exception {
        String responseBody = this.mvc.perform(
                get("/products")
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JavaType responseType = objectMapper.getTypeFactory().constructCollectionType(List.class, ProductDetail.class);

        List<ProductDetail> productDetails = objectMapper.readValue(responseBody, responseType);

        assertThat(productDetails.size()).isEqualTo(1);
        assertThat(productDetails.get(0).name).isEqualTo("Jelly Beans");
        assertThat(productDetails.get(0).version).isEqualTo(0);
        assertThat(productDetails.get(0).id).isEqualTo(1);
    }

    @Sql({"classpath:clear-database.sql"})
    @Test
    public void postShoulCreateANewProduct() throws Exception {
        ProductDetail newProduct = new ProductDetail(0L, "Robusta Beans", 0);
        String requestBody = objectMapper.writeValueAsString(newProduct);

        String responseBody = this.mvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestBody)
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/products/1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDetail createdProduct = objectMapper.readValue(responseBody, ProductDetail.class);
        assertThat(createdProduct.id).isNotZero();
        assertThat(createdProduct.name).isEqualTo(newProduct.name);
        assertThat(createdProduct.version).isEqualTo(0);
    }

    @Sql({"classpath:clear-database.sql", "classpath:products.sql"})
    @Test
    public void putShouldUpdateProduct() throws Exception {

        ProductDetail productDetail = new ProductDetail(1L, "Arabica Beans", 0);

        String requestBody = objectMapper.writeValueAsString(productDetail);

        String responseBody = this.mvc.perform(
                put("/products/"+productDetail.id)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ProductDetail updatedProduct = objectMapper.readValue(responseBody, ProductDetail.class);
        assertThat(updatedProduct.id).isEqualTo(productDetail.id);
        assertThat(updatedProduct.name).isEqualTo(productDetail.name);
        assertThat(updatedProduct.version).isEqualTo(productDetail.version+1);
    }
}
