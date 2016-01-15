package net.gfu.seminar.spring.helloworld.web;

/**
 * Created by user1 on 14.01.2016.
 */
import net.gfu.seminar.spring.helloworld.ApplicationConfig;
import net.gfu.seminar.spring.helloworld.GuestDao;
import net.gfu.seminar.spring.helloworld.HibernateConfig;
import net.gfu.seminar.spring.helloworld.JdbcConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"net.gfu.seminar.spring.helloworld.web"})
@Import({ApplicationConfig.class})
public class DispatcherConfig {

    @Bean
    public GuestController guestController(GuestDao dao) {
        return new GuestController(dao);
    }


}