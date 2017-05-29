package monolith.usecases;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@ComponentScan("monolith.usecases.impl")
public class UseCasesConfiguration {

}
