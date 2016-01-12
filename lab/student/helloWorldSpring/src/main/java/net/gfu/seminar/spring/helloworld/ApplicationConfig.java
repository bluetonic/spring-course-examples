package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
@PropertySource(value = {"classpath:/guest.properties"})
public class ApplicationConfig {

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
    public SpecialGuest specialGuest(@Value("${firstname}") String firstname, @Value("${lastname}") String lastname ) {
        return new SpecialGuest(firstname,lastname);
    }

    @Bean
    public Guest guest() {
        return new Guest("Reiner", "Unsinn");
    }



}
