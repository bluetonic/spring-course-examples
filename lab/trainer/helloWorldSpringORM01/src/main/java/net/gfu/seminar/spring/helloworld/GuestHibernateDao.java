package net.gfu.seminar.spring.helloworld;

import java.util.List;
import java.util.Scanner;

import net.gfu.seminar.spring.helloworld.Guest;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository to access {@link Guest}s implementation based on plain Hibernate 4.x API.
 * 
 * @author tf
 *
 */
@Repository
@Transactional
public class GuestHibernateDao implements GuestDao {

	private SessionFactory sessionFactory;
	
	public GuestHibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long create(Guest guest) {
		return (Long) sessionFactory.getCurrentSession().save(guest);
	}

	@Override
	public Guest findById(Long id) {
		return (Guest) this.sessionFactory.getCurrentSession().get(Guest.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> findByName(String name) {

		Scanner scanner = new Scanner(name);
		final Object[] args = new Object[]{
				scanner.next(),
				scanner.next()
		};
		List<Guest> list = this.sessionFactory.getCurrentSession()
				.createQuery("from Guest as g where g.fristname :firstname AND g.lastName = :lastname")
				.setString("firstname", args[0].toString()).setString("lastname", args[1].toString()).list();
		System.out.println("-------------------------------"+args[0].toString());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> findAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from Guest").list();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Guest update(Guest guest) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(guest);
		return guest;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Guest guest) {
		this.sessionFactory.getCurrentSession().delete(guest);
	}

}
