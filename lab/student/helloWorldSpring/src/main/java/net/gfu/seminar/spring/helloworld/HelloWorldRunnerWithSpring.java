package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * HelloWorld using Spring Framework BeanFactory.
 *
 * @author tf
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html#beans-introduction">Spring DI</a>
 */
public class HelloWorldRunnerWithSpring {

	public static void main(String[] args) {
		/// XXX Examples for creating the Spring Container
		// 1. The Bean Factory
		// loading the Spring XML configuration file and initializing the BeanFactory
		// this file is located in the classpath.
		// TODO Open in your IDE the project settings and check the Java Build Path
		//Resource resource = new ClassPathResource("applicationContext.xml"); //located in src/main/resources

		// this creates a new instance of the Spring Container aka BeanFactory
		// BeanFactory beanFactory = new XmlBeanFactory(resource);

		// String files = "applicationContext.xml";
		// ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(files);

		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		// here we retrieve an instance of Greeting from the Spring BeanFactory

		// Hier wird alte beanFactory benutzt. Finger Weg!! Depricated
		//Greeting greeting = beanFactory.getBean("greeting", Greeting.class);

		Greeting greeting = context.getBean("greeting", Greeting.class);

		System.out.println(greeting.welcome());
		context.close();



	}
}
