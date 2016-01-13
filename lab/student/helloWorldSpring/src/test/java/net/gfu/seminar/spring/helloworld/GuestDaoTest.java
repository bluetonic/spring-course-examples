package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class})
public class GuestDaoTest {
	
	@Autowired
	private GuestDao dao;
	
	@Autowired
	@Qualifier("guest")
	private Guest guest;
	
	@Test
	public void testCreate() {
		assertNotNull(guest);
		assertNotNull(dao);
		long numberOfAffectedRows = dao.create(guest);
	}
	
	@Test
	public void testFindById() {
		Long id = Long.valueOf(1);
		assertNotNull(dao.findById(id));
	}

	@Test
	public void testFindByName() {
		String name = "Hans Dampf";
		List<Guest> list = dao.findByName(name);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testFindAll() {
		List<Guest> all = dao.findAll();
		assertTrue( all.size() > 0 );
	}

	@Test
	public void testUpdate() {
		dao.update(guest);
	}

	@Test
	public void testRemove() {
		dao.remove(guest);
	}
	
}
