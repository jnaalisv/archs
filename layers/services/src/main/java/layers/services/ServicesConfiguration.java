package layers.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan("layers.services.impl")
public class ServicesConfiguration {

}
