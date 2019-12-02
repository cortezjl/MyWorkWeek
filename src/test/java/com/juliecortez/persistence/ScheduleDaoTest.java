package com.juliecortez.persistence;

import com.juliecortez.entity.ScheduleDetail;
import com.juliecortez.entity.Schedule;
import com.juliecortez.entity.User;
import com.juliecortez.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleDaoTest {

    /**
     * The Dao variable for the ScheduleDao object.
     */
    GenericDao scheduleDao;



    /**
     * Sets up before each test - instantiates a ScheduleDao object
     */
    @BeforeEach
    void setUp() {
        scheduleDao = new GenericDao(Schedule.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Gets Schedule by id and tests for success.
     */
    @Test
    void getByIdSuccess() {
        Schedule retrievedSchedule = (Schedule)scheduleDao.getById(1);
        // validate an entry was retrieved
        assertNotNull(retrievedSchedule);
        // validate one of the object values is the value expected
        assertEquals(LocalDate.parse("2019-11-11"), retrievedSchedule.getStartDate());
    }

    /**
     * Gets all Schedules, tests for success.
     */
    @Test
    void getAllSuccess() {
        List<Schedule> schedules = scheduleDao.getAll();
        assertEquals(1, schedules.size());
    }


    /**
     * Verify successful insert of a Schedule with a ScheduleDetail, Schedule should have at least one Schedule Detail
     */
    @Test
    void insertWithScheduleDetailSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        // Instantiate and retrieve User data by id Schedule
        User user = (User)userDao.getById(1);
        // Instantiate and create a new Schedule
        Schedule newSchedule = new Schedule(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-01-08"));
        // Instantiate and create a new ScheduleDetail and add the Schedule and User objects to the ScheduleDetail object
        ScheduleDetail scheduleDetail = new ScheduleDetail();
        scheduleDetail.setStartTime1("open");
        scheduleDetail.setEndTime1( "04:00 PM");
        scheduleDetail.setUserName("admin");
        scheduleDetail.setSchedule(newSchedule);
        scheduleDetail.setUser(user);
        // add the ScheduleDetail to the set of ScheduleDetails for the Schedule
        newSchedule.addScheduleDetail(scheduleDetail);
        // insert the Schedule object, which will create the Role as well
        int id = scheduleDao.insert(newSchedule);
        Schedule insertedSchedule = (Schedule)scheduleDao.getById(id);

        assertNotEquals(0,id);
        assertEquals(1, insertedSchedule.getScheduleDetails().size());
    }

    /**
     * Verify successful delete of Schedule
     */
    @Test
    void deleteSuccess() {
        // Instantiate and create a new Schedule
        Schedule newSchedule = new Schedule(LocalDate.parse("2018-01-01"), LocalDate.parse("2018-01-08"));
        // insert the Schedule object, which will create the Role as well
        int id = scheduleDao.insert(newSchedule);
        scheduleDao.delete((Schedule)scheduleDao.getById(id));
        assertNull((Schedule)scheduleDao.getById(id));
    }

    /**
     * Verify successful update of Schedule
     */
    @Test
    void updateSuccess() {
        String newStartDate = "2019-11-12";
        Schedule scheduleToUpdate = (Schedule)scheduleDao.getById(1);
        scheduleToUpdate.setStartDate(LocalDate.parse(newStartDate));
        scheduleDao.saveOrUpdate(scheduleToUpdate);
        Schedule retrievedSchedule = (Schedule)scheduleDao.getById(1);
        assertEquals(LocalDate.parse(newStartDate), retrievedSchedule.getStartDate());
    }

}
