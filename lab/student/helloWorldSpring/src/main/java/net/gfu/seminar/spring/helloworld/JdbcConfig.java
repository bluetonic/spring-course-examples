package net.gfu.seminar.spring.helloworld;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Created by user1 on 14.01.2016.
 */
@Configuration
@Profile("jdbc")
public class JdbcConfig {

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
    @Bean
    public GuestJdbcDao guestDao(DataSource dataSource) {
        GuestJdbcDao guestJdbcDao = new GuestJdbcDao();
        guestJdbcDao.setDataSource(dataSource);
        return guestJdbcDao ;
    }


    @Bean
    public DataSourceInitializer dsi(DataSource dataSource) {
        DataSourceInitializer dsi = new DataSourceInitializer();
        dsi.setDataSource(dataSource);
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setScripts(new Resource[] {
                new ClassPathResource("drop_hsql_schema.sql"),
                new ClassPathResource("create_hsql_schema.sql"),
                new ClassPathResource("insert_testdata_hsql.sql")});
        dsi.setDatabasePopulator(databasePopulator);
        return dsi;
    }
}
