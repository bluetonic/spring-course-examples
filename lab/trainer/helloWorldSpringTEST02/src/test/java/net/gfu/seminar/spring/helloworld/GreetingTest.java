package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class GreetingTest {

	private static final Logger LOG = Logger.getLogger(GreetingTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LOG.info("setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOG.info("tearDownAfterClass");
	}
	
	@Inject
	private GreetingService greeting;
	@Inject
	private Guest guest;

	@Before
	public void setUp() throws Exception {
		LOG.info("setUp");
	}

	@After
	public void tearDown() throws Exception {
		LOG.info("tearDown");
	}

	@Test(timeout=1000)
	public void testWelcome() {
		assertNotNull(greeting.welcome());
		LOG.debug(greeting.welcome());
	}
	
	@Test
	public void testGuest() {
	 	assertEquals("Hans Dampf" , guest.getName());
	}

}
