package net.gfu.seminar.spring.helloworld;

import java.util.List;

/**
 * Created by user1 on 13.01.2016.
 * CRUD DAO for Guest.
 */
public interface GuestDao {
    Long create(Guest guest);
    Guest findById(Long id);
    List<Guest> findByName(String name);
    List<Guest> findAll();
    Guest update(Guest guest);
    void remove(Guest guest);
}
