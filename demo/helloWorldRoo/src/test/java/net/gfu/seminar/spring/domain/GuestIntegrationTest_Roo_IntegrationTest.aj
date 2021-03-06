// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.gfu.seminar.spring.domain;

import java.util.List;
import net.gfu.seminar.spring.domain.Guest;
import net.gfu.seminar.spring.domain.GuestDataOnDemand;
import net.gfu.seminar.spring.domain.GuestIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GuestIntegrationTest_Roo_IntegrationTest {
    
    declare @type: GuestIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: GuestIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: GuestIntegrationTest: @Transactional;
    
    @Autowired
    GuestDataOnDemand GuestIntegrationTest.dod;
    
    @Test
    public void GuestIntegrationTest.testCountGuests() {
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", dod.getRandomGuest());
        long count = Guest.countGuests();
        Assert.assertTrue("Counter for 'Guest' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void GuestIntegrationTest.testFindGuest() {
        Guest obj = dod.getRandomGuest();
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Guest' failed to provide an identifier", id);
        obj = Guest.findGuest(id);
        Assert.assertNotNull("Find method for 'Guest' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Guest' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void GuestIntegrationTest.testFindAllGuests() {
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", dod.getRandomGuest());
        long count = Guest.countGuests();
        Assert.assertTrue("Too expensive to perform a find all test for 'Guest', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Guest> result = Guest.findAllGuests();
        Assert.assertNotNull("Find all method for 'Guest' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Guest' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void GuestIntegrationTest.testFindGuestEntries() {
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", dod.getRandomGuest());
        long count = Guest.countGuests();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Guest> result = Guest.findGuestEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Guest' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Guest' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void GuestIntegrationTest.testFlush() {
        Guest obj = dod.getRandomGuest();
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Guest' failed to provide an identifier", id);
        obj = Guest.findGuest(id);
        Assert.assertNotNull("Find method for 'Guest' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyGuest(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Guest' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void GuestIntegrationTest.testMergeUpdate() {
        Guest obj = dod.getRandomGuest();
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Guest' failed to provide an identifier", id);
        obj = Guest.findGuest(id);
        boolean modified =  dod.modifyGuest(obj);
        Integer currentVersion = obj.getVersion();
        Guest merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Guest' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void GuestIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", dod.getRandomGuest());
        Guest obj = dod.getNewTransientGuest(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Guest' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Guest' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Guest' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void GuestIntegrationTest.testRemove() {
        Guest obj = dod.getRandomGuest();
        Assert.assertNotNull("Data on demand for 'Guest' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Guest' failed to provide an identifier", id);
        obj = Guest.findGuest(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Guest' with identifier '" + id + "'", Guest.findGuest(id));
    }
    
}
