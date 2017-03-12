package layers.persistence.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DatasourceConfiguration {

    @Value("${database.user}")
    private String dbUser;

    @Value("${database.password}")
    private String dbPassword;

    @Value("${database.name}")
    private String dbName;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", dbUser);
        props.setProperty("dataSource.password", dbPassword);
        props.setProperty("dataSource.databaseName", dbName);
        return new HikariDataSource(new HikariConfig(props));
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setBaselineOnMigrate(false);
        flyway.setLocations("classpath:/db/migration/");
        flyway.setDataSource(dataSource);
        return flyway;
    }
}
