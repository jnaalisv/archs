package monolith.http;

import monolith.persistence.PersistenceConfiguration;
import monolith.usecases.UseCasesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({PersistenceConfiguration.class, UseCasesConfiguration.class})
@SpringBootApplication(exclude= {HibernateJpaAutoConfiguration.class})
public class MonolithHttpApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonolithHttpApiApplication.class, args);
    }
}
