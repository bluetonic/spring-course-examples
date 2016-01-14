package net.gfu.seminar.spring.helloworld;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
@PropertySource(value = {"classpath:/guest.properties", "classpath:/jdbc.properties"})
@Import({HibernateConfig.class, JdbcConfig.class})
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






}
