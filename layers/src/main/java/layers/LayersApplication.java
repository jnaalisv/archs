package layers;
import layers.persistence.HibernateConfiguration;
import layers.services.ServicesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@Import({
        HibernateConfiguration.class,
        ServicesConfiguration.class,
        WebMvcConfiguration.class
})
@EnableAutoConfiguration(exclude= {HibernateJpaAutoConfiguration.class})
@ComponentScan("layers.http")
public class LayersApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayersApplication.class, args);
    }
}
