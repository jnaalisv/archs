package monolith.persistence;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Properties;

public class DatasourceConfiguration {

    @Value("${datasource.username}")
    private String dbUser;

    @Value("${datasource.password}")
    private String dbPassword;

    @Value("${datasource.database_name}")
    private String dbName;

    @Bean(destroyMethod = "close")
    private DataSource hikariDataSource() { //TODO  HikariDataSource
        Properties props = new Properties();
        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", dbUser);
        props.setProperty("dataSource.password", dbPassword);
        props.setProperty("dataSource.databaseName", dbName);

        HikariConfig hikariConfig = new HikariConfig(props);
        hikariConfig.setAutoCommit(true);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Primary
    public DataSource dataSource(DataSource hikariDataSource) {
        logger.info("creating proxyDS");

        return ProxyDataSourceBuilder
                .create(hikariDataSource)
                .name("proxyDS")
                .logQueryBySlf4j(SLF4JLogLevel.INFO)
                .build();
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
