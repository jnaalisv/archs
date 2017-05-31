package layers.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import layers.config.SerializationConfiguration;
import layers.services.ProductDetail;
import layers.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

    private final ObjectMapper objectMapper = SerializationConfiguration.objectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(productController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    public void getProductsShouldReturnProducts() throws Exception {
        given(this.productService.getProducts())
                .willReturn(Arrays.asList(new ProductDetail(1, "Cool Beans", 0L)));

        this.mvc.perform(
                get("/products")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[ {\n" +
                        "  \"id\" : 1,\n" +
                        "  \"name\" : \"Cool Beans\",\n" +
                        "  \"version\" : 0\n" +
                        "} ]"));
    }


    @Test
    public void postShouldReturnCreatedProduct() throws Exception {
        this.mvc.perform(
                post("/products")
                        .content("{\"name\":\"Cool Beans\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\n" +
                        "  \"id\" : 0,\n" +
                        "  \"name\" : \"Cool Beans\",\n" +
                        "  \"version\" : 0\n" +
                        "}"));
    }
}
