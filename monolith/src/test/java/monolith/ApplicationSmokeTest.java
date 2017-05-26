package monolith;

import monolith.http.MonolithHttpApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MonolithHttpApiApplication.class
)
public class ApplicationSmokeTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ShouldNotBlowUp() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("/products", List.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
