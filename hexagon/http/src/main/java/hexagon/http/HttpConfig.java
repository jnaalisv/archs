package hexagon.http;

import hexagon.application.api.ApplicationConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("hexagon.persistence")
@Import(ApplicationConfiguration.class)
@EnableAutoConfiguration(exclude= {HibernateJpaAutoConfiguration.class})
@ComponentScan("hexagon.http")
public class HttpConfig {
}
