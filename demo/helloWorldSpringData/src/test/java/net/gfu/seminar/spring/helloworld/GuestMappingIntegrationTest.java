package net.gfu.seminar.spring.helloworld;

import static net.gfu.seminar.spring.helloworld.JPAAssertions.assertTableExists;
import static net.gfu.seminar.spring.helloworld.JPAAssertions.assertTableHasColumn;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JPAConfiguration.class })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class GuestMappingIntegrationTest {

	@PersistenceContext
	private EntityManager manager;

	@Test
	public void thatItemCustomMappingWorks() throws Exception {
		assertTableExists(manager, "GUEST");
		assertTableHasColumn(manager, "GUEST", "FIRSTNAME");
		assertTableHasColumn(manager, "GUEST", "LASTNAME");
	}

}