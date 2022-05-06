package com.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfigure {

    @Bean
    public DataSource dataSource(
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password,
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.hikari.driver-class-name}") String className) {

        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(className);
        hikariConfig.setJdbcUrl(url);

        return new HikariDataSource(hikariConfig);
    }

//    @Bean
//    public NamedParameterJdbcTemplate jdbcTemplate(DataSource dataSource) {
//        return new NamedParameterJdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public SimpleJdbcInsert jdbcInsert(DataSource dataSource) {
//        return new SimpleJdbcInsert(dataSource);
//    }
//
//    @Bean
//    public Flyway flyway(
//            DataSource dataSource,
//            @Value("${spring.flyway.oracle-wallet-location}") String location,
//            @Value("${spring.flyway.baseline-on-migrate}") Boolean baseLineOnMigrate,
//            @Value("${spring.flyway.out-of-order}") Boolean outOfOrder) {
//        return Flyway.configure()
//                .outOfOrder(outOfOrder)
//                .baselineOnMigrate(baseLineOnMigrate)
//                .locations(location)
//                .dataSource(dataSource)
//                .load();
//    }
//
//    @Bean
//    public InitializingBean flywayMigrate(Flyway flyway) {
//        return flyway::migrate;
//    }

}
