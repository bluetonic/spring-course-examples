package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
public class ApplicationConfig {
    @Bean
    public Greeting greeting(){
        return  new Greeting(specialGuest());
    }

    @Bean
    public SpecialGuest specialGuest() {
        return new SpecialGuest("Mr.","VIP");
    }

    @Bean
    public Guest guest() {
        return new Guest("Reiner", "Unsinn");
    }



}
