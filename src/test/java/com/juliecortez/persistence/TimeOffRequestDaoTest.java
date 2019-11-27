package com.juliecortez.persistence;


import com.juliecortez.entity.TimeOffRequest;
import com.juliecortez.entity.User;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type TimeOffRequest dao test.
 */
class TimeOffRequestDaoTest {

    // TimeOffRequestDao dao;
    GenericDao timeOffRequestDao;


    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        // dao = new timeOffRequestDao();
        timeOffRequestDao = new GenericDao(TimeOffRequest.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all TimeOffRequest successfully.
     */
    @Test
    void getAllSuccess() {
        List<TimeOffRequest> timeOffRequests = timeOffRequestDao.getAll();
        assertEquals(1, timeOffRequests.size());
    }


    /**
     * Verifies a Role is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        TimeOffRequest retrievedTimeOffRequest = (TimeOffRequest)timeOffRequestDao.getById(1);
        assertNotNull(retrievedTimeOffRequest);
        assertEquals("12:00 AM", retrievedTimeOffRequest.getStartTime());
    }

    /**
     * Verify successful insert of a TimeOffRequest
     */
    @Test
    void insertSuccess() {

        GenericDao userDao = new GenericDao(User.class);
        // retrieve user object by id
        User user = (User)userDao.getById(1);
        // create the new time off request including the user object
        TimeOffRequest newTimeOffRequest = new TimeOffRequest("admin", LocalDate.parse("2019-12-24"), LocalDate.parse("2019-12-24"), "09:00 AM", "05:00 PM", user);
        // add the Role to the set of Roles for the user object
        user.addTimeOffRequest(newTimeOffRequest);
        // insert the time off request, which will update the user object
        int id = timeOffRequestDao.insert(newTimeOffRequest);

        assertNotEquals(0,id);
        TimeOffRequest insertedTimeOffRequest = (TimeOffRequest)timeOffRequestDao.getById(id);
        assertEquals("09:00 AM", insertedTimeOffRequest.getStartTime());
        assertNotNull(insertedTimeOffRequest.getUser());
        // For the inserted Role object, get the user object and get the users first name, and compare to expected value
        assertEquals("System", insertedTimeOffRequest.getUser().getFirstName());
        assertEquals(newTimeOffRequest, insertedTimeOffRequest);
    }


    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        timeOffRequestDao.delete(timeOffRequestDao.getById(1));
        assertNull(timeOffRequestDao.getById(1));
    }

    /**
     * Verify successful update of Role
     */
    @Test
    void updateSuccess() {
        String endTime = "01:30 PM";
        TimeOffRequest timeOffRequestToUpdate = (TimeOffRequest)timeOffRequestDao.getById(1);
        timeOffRequestToUpdate.setEndTime(endTime);
        timeOffRequestDao.saveOrUpdate(timeOffRequestToUpdate);
        TimeOffRequest retrievedTimeOffRequest = (TimeOffRequest)timeOffRequestDao.getById(1);
        assertEquals(endTime, retrievedTimeOffRequest.getEndTime());
        assertEquals(timeOffRequestToUpdate, retrievedTimeOffRequest);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<TimeOffRequest> timeOffRequests = timeOffRequestDao.getByPropertyEqual("userName", "admin");
        assertEquals(1, timeOffRequests.size());
        assertEquals(1, timeOffRequests.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<TimeOffRequest> timeOffRequests = timeOffRequestDao.getByPropertyLike("userName", "a");
        assertEquals(1, timeOffRequests.size());
    }
}
