package layers.persistence;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Import(DatasourceConfiguration.class)
@ComponentScan("layers.persistence.hibernate")
public class HibernateConfiguration {

    private static Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        hibernateProperties.put("hibernate.hbm2ddl.auto","validate");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.generate_statistics", "true");
        hibernateProperties.put("hibernate.jdbc.batch_size", 30);
        hibernateProperties.put("hibernate.order_updates", "true");
        hibernateProperties.put("hibernate.order_inserts", "true");
        hibernateProperties.put("hibernate.jdbc.batch_versioned_data", "true");
        return hibernateProperties;
    }

    @DependsOn("flyway")
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("layers.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
