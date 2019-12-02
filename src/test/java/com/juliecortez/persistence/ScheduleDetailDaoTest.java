package com.juliecortez.persistence;


import com.juliecortez.entity.Schedule;
import com.juliecortez.entity.ScheduleDetail;
import com.juliecortez.entity.User;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type ScheduleDetail dao test.
 */
class ScheduleDetailDaoTest {

    GenericDao scheduleDetailDao;


    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        scheduleDetailDao = new GenericDao(ScheduleDetail.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets all ScheduleDetail successfully.
     */
    @Test
    void getAllSuccess() {
        List<ScheduleDetail> scheduleDetails = scheduleDetailDao.getAll();
        assertEquals(1, scheduleDetails.size());
    }


    /**
     * Verifies a ScheduleDetail is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        ScheduleDetail retrievedScheduleDetail = (ScheduleDetail)scheduleDetailDao.getById(1);
        assertNotNull(retrievedScheduleDetail);
        assertEquals("open", retrievedScheduleDetail.getStartTime1());
    }

    /**
     * Verify successful insert of a ScheduleDetail
     */
    @Test
    void insertSuccess() {

        GenericDao userDao = new GenericDao(User.class);
        // retrieve user object by id
        User user = (User)userDao.getById(1);
        GenericDao scheduleDao = new GenericDao(Schedule.class);
        // retrieve schedule object by id
        Schedule schedule = (Schedule)scheduleDao.getById(1);
        // create the new ScheduleDetail including the schedule and user objects
        ScheduleDetail newScheduleDetail = new ScheduleDetail("open", "04:00 PM", "open", "04:00 PM","open", "04:00 PM","open",
                "04:00 PM","open", "04:00 PM","open", "02:00 PM","open", "02:00 PM", "admin", schedule, user);
        // add the Role to the set of Roles for the user object
        schedule.addScheduleDetail(newScheduleDetail);
        // insert the ScheduleDetail, which will update the schedule object
        int id = scheduleDetailDao.insert(newScheduleDetail);

        assertNotEquals(0,id);
        ScheduleDetail insertedScheduleDetail = (ScheduleDetail)scheduleDetailDao.getById(id);
        assertEquals("open", insertedScheduleDetail.getStartTime1());
        assertEquals(newScheduleDetail, insertedScheduleDetail);
    }


    /**
     * Verify successful delete of order
     */
    @Test
    void deleteSuccess() {
        scheduleDetailDao.delete(scheduleDetailDao.getById(1));
        assertNull(scheduleDetailDao.getById(1));
    }

    /**
     * Verify successful update of Role
     */
    @Test
    void updateSuccess() {
        String startTime = "09:00 AM";
        ScheduleDetail scheduleDetailToUpdate = (ScheduleDetail)scheduleDetailDao.getById(1);
        scheduleDetailToUpdate.setStartTime1(startTime);
        scheduleDetailDao.saveOrUpdate(scheduleDetailToUpdate);
        ScheduleDetail retrievedScheduleDetail = (ScheduleDetail)scheduleDetailDao.getById(1);
        assertEquals(startTime, retrievedScheduleDetail.getStartTime1());
        assertEquals(scheduleDetailToUpdate, retrievedScheduleDetail);
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<ScheduleDetail> scheduleDetails = scheduleDetailDao.getByPropertyEqual("startTime1", "open");
        assertEquals(1, scheduleDetails.size());
        assertEquals(1, scheduleDetails.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<ScheduleDetail> scheduleDetails = scheduleDetailDao.getByPropertyLike("startTime1", "o");
        assertEquals(1, scheduleDetails.size());
    }
}

