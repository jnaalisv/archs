package layers.http;

import layers.application.api.ProductDetail;
import layers.application.api.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProductsShouldReturnProducts() throws Exception {
        given(this.productService.getProducts())
                .willReturn(Arrays.asList(new ProductDetail(1, "Cool Beans")));

        this.mvc.perform(
                get("/products")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"name\":\"Cool Beans\"}]"));
    }


    @Test
    public void addingProductShouldReturnAddedProduct() throws Exception {
        this.mvc.perform(
                post("/products")
                        .content("{\"name\":\"Cool Beans\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"id\":0,\"name\":\"Cool Beans\"}"));
    }

}
