package layers.http;

import layers.application.api.ProductDetail;
import layers.application.api.ProductService;
import layers.http.resources.ProductController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

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
