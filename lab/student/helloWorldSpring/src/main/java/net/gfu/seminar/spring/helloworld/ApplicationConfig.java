package net.gfu.seminar.spring.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by im on 12.01.2016.
 */

@Configuration
public class ApplicationConfig {
    @Bean
    public Greeting greeting(){
        return new Greeting(guest());
    }

    @Bean
    public Guest guest() {
        return new Guest("Reiner", "Unsinn");
    }
}
