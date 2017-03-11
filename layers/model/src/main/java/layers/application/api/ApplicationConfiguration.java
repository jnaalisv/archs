package layers.application.api;

import layers.persistence.config.HibernateConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Import(HibernateConfiguration.class)
@ComponentScan("layers.application.impl")
public class ApplicationConfiguration {
}
