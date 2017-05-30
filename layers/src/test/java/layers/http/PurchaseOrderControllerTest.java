package layers.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import layers.config.SerializationConfiguration;
import layers.services.PurchaseOrderDetail;
import layers.services.PurchaseOrderService;
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

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseOrderControllerTest {

    @Mock
    private PurchaseOrderService purchaseOrderService;

    @InjectMocks
    private PurchaseOrderController purchaseOrderController;

    private MockMvc mvc;

    private final ObjectMapper objectMapper = new SerializationConfiguration().objectMapper();

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .standaloneSetup(purchaseOrderController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

    @Test
    public void shouldGetPurchaseOrders() throws Exception {
        PurchaseOrderDetail purchaseOrder1 = new PurchaseOrderDetail();
        purchaseOrder1.id = 1L;
        purchaseOrder1.version = 3L;
        purchaseOrder1.createTime = LocalDateTime.of(2017, 5, 5, 12, 5);

        PurchaseOrderDetail purchaseOrder2 = new PurchaseOrderDetail();
        purchaseOrder2.id = 2L;
        purchaseOrder2.version = 1L;
        purchaseOrder2.createTime = LocalDateTime.of(2017, 4, 25, 11, 45);

        given(this.purchaseOrderService.getPurchaseOrders()).willReturn(Arrays.asList(purchaseOrder1));

        this.mvc.perform(
                get("/purchase-orders")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string("[ {\n  \"id\" : 1,\n  \"createTime\" : \"2017-05-05T12:05:00\",\n  \"version\" : 3,\n  \"orderLines\" : [ ]\n} ]"));
    }
}
