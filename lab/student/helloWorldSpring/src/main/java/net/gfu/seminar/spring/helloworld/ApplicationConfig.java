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

import javax.sql.DataSource;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
@PropertySource(value = {"classpath:/guest.properties", "classpath:/jdbc.properties"})
public class ApplicationConfig {


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
    public DataSource dataSource(@Value("${jdbc.driverClassName}") String driverClassName, @Value("${jdbc.url}") String url, @Value("${jdbc.username}") String username, @Value("${jdbc.password}") String password) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }



}
