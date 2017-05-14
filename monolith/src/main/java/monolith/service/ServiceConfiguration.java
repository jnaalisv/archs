package monolith.service;

import monolith.persistence.PersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(PersistenceConfiguration.class)
@EnableTransactionManagement
@ComponentScan("monolith.service.impl")
public class ServiceConfiguration {

}
