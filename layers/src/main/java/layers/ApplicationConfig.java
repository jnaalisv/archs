package layers;

import layers.config.WebMvcConfiguration;
import layers.persistence.HibernateConfiguration;
import layers.services.ServicesConfiguration;
import org.springframework.context.annotation.Import;

@Import({
        HibernateConfiguration.class,
        ServicesConfiguration.class,
        WebMvcConfiguration.class})
public class ApplicationConfig {
}
