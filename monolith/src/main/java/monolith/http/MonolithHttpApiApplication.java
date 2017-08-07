package monolith.http;

import monolith.application.ServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(ServiceConfiguration.class)
@SpringBootApplication(exclude= {HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class MonolithHttpApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithHttpApiApplication.class, args);
    }
}
