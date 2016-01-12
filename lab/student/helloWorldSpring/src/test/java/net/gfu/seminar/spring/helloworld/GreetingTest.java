package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

// XML based Configuration
//@ContextConfiguration(location={"classpatch:/applicationConfig.xml"} )

@ContextConfiguration(classes={ApplicationConfig.class} )
public class GreetingTest {

	@Autowired
	private Greeting greeting;

	@Test
	public void testWelcome(){
		assertNotNull(greeting.welcome());
	}

	/*
	Wie kann eine Instanz von Greeting in den Unit-
	Test injiziert werden?
	 */


}
