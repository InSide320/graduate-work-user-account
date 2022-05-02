package com.example;

import com.example.config.SpringApplicationConfig;
import com.example.user.UserPersonalData;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AccountApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SpringApplicationConfig.class);

        Flyway flyway = applicationContext.getBean(Flyway.class);
        flyway.migrate();
        NamedParameterJdbcTemplate namedParameterJdbcTemplate
                = (NamedParameterJdbcTemplate) applicationContext.getBean("jdbcTemplate");

    }
}
