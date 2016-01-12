package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
@PropertySource(value = {"classpath:/guest.properties"})
public class ApplicationConfig {


//    @Autowired
//    private Environment environment;

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer pspc(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }

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
    // environment.firstname.equals('Ivan')?'Foo':'Bar'}
    public SpecialGuest specialGuest(@Value("#{environment.firstname?:''}") String firstname, @Value("#{environment.lastname}") String lastname ) {
        return new SpecialGuest(firstname,lastname);
    }

    @Bean
    public Guest guest() {
        return new Guest("Reiner", "Unsinn");
    }



}
