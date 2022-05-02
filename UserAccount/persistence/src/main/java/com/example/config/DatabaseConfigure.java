package com.example.config;

import com.example.database.hikaricp.HikariCpConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@PropertySource("classpath:file/database.properties")
@PropertySource("classpath:file/flyway-config.properties")
public class DatabaseConfigure {
    @Bean
    public DataSource dataSource(
            @Value("${dataSource.user}") String username,
            @Value("${dataSource.password}") String password,
            @Value("${dataSource.className}") String className) throws IOException {

        final HikariConfig hikariConfig = new HikariConfig(String.valueOf(new HikariCpConfig().setFileProperties()));
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public SimpleJdbcInsert jdbcInsert(DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource);
    }

    @Bean
    public Flyway flyway(
            DataSource dataSource,
            @Value("${spring.flyway.locations}") String location,
            @Value("${spring.flyway.base-line-on-migrate}") Boolean baseLineOnMigrate,
            @Value("${spring.flyway.out-of-order}") Boolean outOfOrder) {
        return Flyway.configure()
                .outOfOrder(outOfOrder)
                .baselineOnMigrate(baseLineOnMigrate)
                .locations(location)
                .dataSource(dataSource)
                .load();
    }

    @Bean
    public InitializingBean flywayMigrate(Flyway flyway) {
        return flyway::migrate;
    }

}
