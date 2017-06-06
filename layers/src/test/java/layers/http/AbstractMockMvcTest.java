package layers.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import layers.config.SerializationConfiguration;
import org.jnaalisv.test.springframework.MockMvcRequestBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public abstract class AbstractMockMvcTest {

    private final ObjectMapper objectMapper = SerializationConfiguration.objectMapper();

    @Autowired
    private MockMvc mvc;

    protected MockMvcRequestBuilder httpGet(String urlTemplate, Object... urlVariables) {
        return new MockMvcRequestBuilder(mvc, objectMapper, get(urlTemplate, urlVariables));
    }

    protected MockMvcRequestBuilder httpPost(String urlTemplate, Object... urlVariables) {
        return new MockMvcRequestBuilder(mvc, objectMapper, post(urlTemplate, urlVariables));
    }

    protected MockMvcRequestBuilder httpPut(String urlTemplate, Object... urlVariables) {
        return new MockMvcRequestBuilder(mvc, objectMapper, put(urlTemplate, urlVariables));
    }

    protected MockMvcRequestBuilder httpDelete(String urlTemplate, Object... urlVariables) {
        return new MockMvcRequestBuilder(mvc, objectMapper, delete(urlTemplate, urlVariables));
    }
}
