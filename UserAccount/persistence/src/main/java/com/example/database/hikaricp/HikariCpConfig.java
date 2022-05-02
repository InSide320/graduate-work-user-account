package com.example.database.hikaricp;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.beans.BeanProperty;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Component
public class HikariCpConfig {

    Properties properties = new Properties();

    private static final String DATA_SOURCE_JDBC_URL = "dataSourceClassName";
    private static final String DATA_SOURCE_USER = "dataSource.user";
    private static final String DATA_SOURCE_PASS = "dataSource.password";
    private static final String DATA_SOURCE_SERVER_NAME = "dataSource.serverName";
    private static final String DATA_SOURCE_DATABASE_NAME = "dataSource.databaseName";
    private static final String DATA_SOURCE_PORT_NUMBER = "dataSource.portNumber";

    public static File filePath;

    @BeanProperty
    public File setFileProperties() throws IOException {

        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        final ClassPathResource dbProperties = new ClassPathResource("file/database.properties");

        configurer.setLocation(dbProperties);

        filePath = new File(String.valueOf(configurer));
        properties.setProperty(DATA_SOURCE_JDBC_URL, "org.postgresql.ds.PGSimpleDataSource");
        properties.setProperty(DATA_SOURCE_USER, "InSide320");
        properties.setProperty(DATA_SOURCE_PASS, "pass");
        properties.setProperty(DATA_SOURCE_SERVER_NAME, "localhost");
        properties.setProperty(DATA_SOURCE_DATABASE_NAME, "graduate-work");
        properties.setProperty(DATA_SOURCE_PORT_NUMBER, "5432");
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath, false)) {
            properties.store(fileOutputStream, "dataSourceConfig");
        }
        properties.clear();
        return filePath;
    }
}
