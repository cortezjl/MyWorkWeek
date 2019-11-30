package com.juliecortez.persistence;


import com.juliecortez.entity.TimeOffRequest;
import com.juliecortez.entity.User;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println("start date =" + retrievedTimeOffRequest.getStartDate());
        assertEquals(LocalDateTime.parse("2019-11-15T08:00"), retrievedTimeOffRequest.getStartDate());
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
        TimeOffRequest newTimeOffRequest = new TimeOffRequest("admin", LocalDateTime.parse("2019-12-24T01:00:00"), LocalDateTime.parse("2019-12-24T23:00:00"), user);
        // add the Role to the set of Roles for the user object
        user.addTimeOffRequest(newTimeOffRequest);
        // insert the time off request, which will update the user object
        int id = timeOffRequestDao.insert(newTimeOffRequest);

        assertNotEquals(0,id);
        TimeOffRequest insertedTimeOffRequest = (TimeOffRequest)timeOffRequestDao.getById(id);
        assertEquals(LocalDateTime.parse("2019-12-24T01:00:00"), insertedTimeOffRequest.getStartDate());
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
        LocalDateTime endDate = LocalDateTime.parse("2019-12-24T23:00:00");
        TimeOffRequest timeOffRequestToUpdate = (TimeOffRequest)timeOffRequestDao.getById(1);
        timeOffRequestToUpdate.setEndDate(endDate);
        timeOffRequestDao.saveOrUpdate(timeOffRequestToUpdate);
        TimeOffRequest retrievedTimeOffRequest = (TimeOffRequest)timeOffRequestDao.getById(1);
        assertEquals(endDate, retrievedTimeOffRequest.getEndDate());
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
