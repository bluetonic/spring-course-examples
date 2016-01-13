package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by user1 on 12.01.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class} )
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Timed(millis=100)
    @Test
    public void testValidConnection() throws SQLException{
        assertTrue(dataSource.getConnection().isValid(100));
    }

}
