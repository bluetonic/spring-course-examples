package net.gfu.seminar.spring.helloworld;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by user1 on 13.01.2016.
 */
@Transactional
public class GuestJdbcDao extends JdbcDaoSupport implements GuestDao {

    @Override
    @Transactional(isolation = Isolation.DEFAULT,readOnly = false, propagation = Propagation.SUPPORTS)
    public Long create(Guest guest) {
        final String sql = "INSERT INTO GUESTS (firstname,lastname) VALUES (?,?)";
        final Object[] args = new Object[] {
                guest.getFirstName(),
                guest.getLastName() };
        int updatedRows = this.getJdbcTemplate().update(sql, args);
        return Long.valueOf(updatedRows);
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT)
    public Guest findById(Long id) {
        Guest guest = null;
        final String sql = "SELECT * FROM guests WHERE id= ? ";
        Object[] args = new Object[] { id };
        List<Guest> list = this.getJdbcTemplate().query(sql,
                createRowMapper(), args);

        return list.get(0);
    }

    @Override
    public List<Guest> findByName(String name) {
        Scanner scanner = new Scanner(name);
        final Object[] args = new Object[]{
                scanner.next(),
                scanner.next()
        };
        scanner.close();
        final String sql = "SELECT * FROM guests WHERE firstname= ? and lastname = ? ";

        List<Guest> list = this.getJdbcTemplate().query(sql,
                createRowMapper(), args);
        return list;
    }

    @Override
    public List<Guest> findAll() {
        final String sql = "SELECT * FROM guests";
        Object[] args = new Object[] { };

        List<Guest> list = this.getJdbcTemplate().query(sql,
                createRowMapper(), args);
        return list;
    }

    @Override
    public Guest update(Guest guest) {
        if (guest.getId() == 0) {
            this.create(guest);
        }
        String sql = "UPDATE guests SET firstname = ?, lastname=? WHERE id = ?";
        Object[] args = new Object[] { guest.getFirstName(),
                guest.getLastName(), guest.getId() };
        int updatedRows = this.getJdbcTemplate().update(sql, args);
        return guest;    }

    @Override
    public void remove(Guest guest) {
        final String sql = "DELETE FROM GUESTS WHERE id = ?";
        Object[] args = new Object[] { guest.getId() };
        int updatedRows = this.getJdbcTemplate().update(sql, args);
    }

    private RowMapper<Guest> createRowMapper() {
        return new RowMapper<Guest>() {
            @Override
            public Guest mapRow(ResultSet rs, int row) throws SQLException {
                return new Guest(rs.getLong("id"),
                        rs.getString("firstname"), rs.getString("lastname"));
            }
        };
    }

}