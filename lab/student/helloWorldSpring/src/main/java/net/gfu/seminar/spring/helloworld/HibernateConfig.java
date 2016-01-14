package net.gfu.seminar.spring.helloworld;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by user1 on 14.01.2016.
 */
@Configuration
@Profile("hibernate")
@PropertySource(value = {"classpath:/hibernate.properties"})
public class HibernateConfig {


    @Bean
    public Properties hibernateProperties(  ) throws IOException {
        PropertiesFactoryBean hibernateProperties = new PropertiesFactoryBean();
        hibernateProperties.setLocation(new ClassPathResource("hibernate.properties") {
        });

        return hibernateProperties.getObject();
    }

    @Autowired
    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) throws IOException {

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        // GuestHibernateDao guestHibernateDao = guest;
      //  localSessionFactoryBean.setAnnotatedPackages("net.gfu.seminar.spring.helloworld");
        localSessionFactoryBean.setHibernateProperties(hibernateProperties());
        localSessionFactoryBean.setAnnotatedClasses(Guest.class);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){

        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    public GuestDao hibernateGuestDao(){
        return new GuestHibernateDao();
    }

}
