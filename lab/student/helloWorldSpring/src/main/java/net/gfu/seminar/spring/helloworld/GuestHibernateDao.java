package net.gfu.seminar.spring.helloworld;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by user1 on 13.01.2016.
 */
@Transactional
public class GuestHibernateDao implements GuestDao {

    //@Autowired

    // Statt @Autowired kann auch CDI Annotation @Inject verwendet werden
    // Dependency muss hinzugefügt.
    @Inject
    private SessionFactory sessionFactory;

    @Override
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
        return this.sessionFactory.getCurrentSession()
                .createQuery("from Guest as g where g.lastName = :name")
                .setString("name", name).list();
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
    public void remove(Guest guest) {
        this.sessionFactory.getCurrentSession().delete(guest);
    }

}
