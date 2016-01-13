package net.gfu.seminar.spring.helloworld;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
@PropertySource(value = {"classpath:/guest.properties", "classpath:/jdbc.properties"})
@EnableTransactionManagement
public class ApplicationConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
//    @Autowired
//    private Environment environment;

    // Needed for java based Configuration
    @Bean
    public static PropertySourcesPlaceholderConfigurer pspc(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Greeting greeting(SpecialGuest specialGuest){
        return  new Greeting(specialGuest);
    }
/*
    @Bean
    public SpecialGuest specialGuest() {
        return new SpecialGuest("Mr.","VIP");
    }
*/

    @Bean @Scope("prototype")
    // public SpecialGuest specialGuest(@Value("${firstname}") String firstname, @Value("${lastname}") String lastname ) {
    public SpecialGuest specialGuest(@Value("#{environment.firstname?:''}") String firstname, @Value("#{environment.lastname}") String lastname ) {
        return new SpecialGuest(firstname,lastname);
    }

    @Bean
    public Guest guest() {
        return new Guest("Reiner", "Unsinn");
    }

    @Bean
    public DataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName, @Value("${jdbc.url}") String url,
                                 @Value("${jdbc.username}") String username, @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
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


    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


}
