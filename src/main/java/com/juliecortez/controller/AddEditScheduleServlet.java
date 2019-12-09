package com.juliecortez.controller;

import com.juliecortez.entity.Schedule;
import com.juliecortez.entity.ScheduleDetail;
import com.juliecortez.entity.User;
import com.juliecortez.persistence.GenericDao;
import com.juliecortez.persistence.SessionFactoryProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.List;

/**
 * Edit the user
 *
 * @author JCortez
 */
@WebServlet(
    urlPatterns = {"/addEditScheduleServlet"}
)

public class AddEditScheduleServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, MM/dd/yyyy");
    private DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get parameter that identifies if doing an add or edit
        String actionToPerform = request.getParameter("actionToPerform");
        int idToProcess = 0;
        String message = "";
        GenericDao scheduleDao = new GenericDao(Schedule.class);
        logger.debug("In post, actionToPerform=" + actionToPerform);

        if (actionToPerform.equals("add")) {
            // set schedule values with values from form
            Schedule schedule = new Schedule(LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter2),
                    LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter2));
            // set values in the schedule details from the form.  For an edit it will perform the updates
            schedule = setScheduleDetailValues(request, schedule, actionToPerform);
            idToProcess = scheduleDao.insert(schedule);
            message = "Schedule has been added";
            Schedule scheduleAdded = (Schedule) scheduleDao.getById(idToProcess);
            request.setAttribute("userAction", "edit");
            request.setAttribute("schedule", scheduleAdded);
            message = "Schedule has been added";
        } else if (actionToPerform.equals("edit")) {
            // Set id of Schedule to edit
            idToProcess = Integer.parseInt(request.getParameter("id"));
            // Create Schedule object and populate by retrieving Schedule object values by id
            Schedule schedule = (Schedule)scheduleDao.getById(idToProcess);
            // update the schedule details associated with the schedule from values entered in the form
            // there are no schedule fields that get updated once a schedule exists
            schedule = setScheduleDetailValues(request, schedule, actionToPerform);
            Schedule scheduleUpdated = (Schedule) scheduleDao.getById(idToProcess);
            request.setAttribute("userAction", "edit");
            logger.info("Schedule is" + scheduleUpdated);
            request.setAttribute("schedule",scheduleUpdated);
            message = "Schedule has been updated";
        }
        // Add a message from adding or updating the User to the session.
        request.setAttribute("scheduleMessage", message);
        // forward the request to sched schedule add/edit page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/scheduleAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao scheduleDao = new GenericDao(Schedule.class);
        String actionToPerform = request.getParameter("userAction");
        logger.debug("In get, userAction=" + actionToPerform);
        // set attributes based on whether user is doing a search or adding a new schedule
        if (actionToPerform.equals("edit")) {
            Schedule schedule = (Schedule)scheduleDao.getById(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("schedule", schedule);
            request.setAttribute("userAction", "edit");
            request.setAttribute("tableHeader", setTableHeaderDates(schedule.getStartDate()));
        } else if (actionToPerform.equals("add")) {
            LocalDate startDate = (LocalDate)request.getAttribute("startDate");
            request.setAttribute("schedule", setValuesForScheduleToAdd(startDate, startDate.plusDays(6)));
            request.setAttribute("userAction", "add");
            request.setAttribute("tableHeader", setTableHeaderDates(startDate));
        }
        logger.debug("Leaving the get and user action is " + request.getAttribute("userAction"));
        // forward the request to the page to add or edit a User
        RequestDispatcher dispatcher = request.getRequestDispatcher("/scheduleAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Sets table header dates for displaying the schedule detail information
     *
     * @param weekStartingDate the week starting date
     * @return the table header dates
     */
    public String setTableHeaderDates(LocalDate weekStartingDate) {
        String tableHeaderValue = "<th>User Name</th>";
        LocalDate nextDay = weekStartingDate;
        String formattedDateTime;
        for (int i = 0; i < 7; i++) {
            tableHeaderValue = tableHeaderValue + "<th>" + nextDay.format(dateTimeFormatter) + "</th>";
            nextDay = nextDay.plusDays(1);
        }

        return tableHeaderValue;
    }

    /**
     * Sets some values for schedule and schedule detail to add.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the values for schedule to add
     */
    public Schedule setValuesForScheduleToAdd(LocalDate startDate, LocalDate endDate) {
        Schedule scheduleToAdd = new Schedule();
        scheduleToAdd.setStartDate(startDate);
        scheduleToAdd.setEndDate(endDate);
        List<User> activeUsers = getActiveUsersByDateRange(startDate, endDate);
        // populate user name and user info  in schedule detail for all users active during the schedule week
        for (User user : activeUsers) {
            ScheduleDetail scheduleDetail = new ScheduleDetail();
            scheduleDetail.setUserName(user.getUserName());
            scheduleDetail.setUser(user);
            scheduleToAdd.addScheduleDetail(scheduleDetail);
        }
        return scheduleToAdd;
    }

    /**
     * Gets users that are active during the date range.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the active users by date range
     */
    public List<User> getActiveUsersByDateRange(LocalDate startDate, LocalDate endDate) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        String hql = "select u FROM User u WHERE u.startDate <= :endDate and u.endDate >= :startDate ";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("startDate",startDate);
        query.setParameter("endDate",endDate);
        List<User> users = query.list();

        //logger.info("size of user list returned from hibernate query is: " + query.list().size());

        session.close();
        return users;
    }

    /**
     * This method will sets the values in the schedule detail fields that are associated with the schedule
     * the fields are set to the values from the form
     *
     * @param request the request
     * @param schedule   the schedule being added or updated
     * @param actionToPerform set to either add or edit
     * @return Schedule the updated schedule and schedule detail information
     */
    private Schedule setScheduleDetailValues(HttpServletRequest request, Schedule schedule, String actionToPerform) {
        GenericDao scheduleDetailDao = new GenericDao(ScheduleDetail.class);
        ScheduleDetail scheduleDetail = null;
        // Get list of schedule detail values from the form
        String[] scheduleDetailValues = request.getParameterValues("detail");
        // Loop through the list of schedule detail values and add or update the schedule(s) to the schedule object
        int fieldCounter = 0;
        int idBeingProcessed = 0;
        for (String fieldValue : scheduleDetailValues) {
            // add 1 to field counter
            fieldCounter = fieldCounter + 1;
            if (fieldCounter == 1 && actionToPerform.equals("add")) {
                scheduleDetail = new ScheduleDetail();
            } else if (fieldCounter == 1 && actionToPerform.equals("edit")) {
                // find the existing ScheduleDetail matching by id
                idBeingProcessed = Integer.parseInt(fieldValue);
                scheduleDetail = (ScheduleDetail)scheduleDetailDao.getById(idBeingProcessed);
            }

            // add the field value from the form to the schedule detail based on the fieldCounter value
            addfieldToScheduleDetail(scheduleDetailDao, scheduleDetail, fieldValue, fieldCounter, request.getParameter("userAction"));
            // reset counter, fieldCounter is for the number of schedule detail fields to populate from form information

            // there are 16 fields we use values from, so after done with field 16 can add or update the schedule detail record
            if (fieldCounter == 16 && actionToPerform.equals("add")) {
                schedule.addScheduleDetail(scheduleDetail);
            } else if (fieldCounter == 16 && actionToPerform.equals("edit")) {
                scheduleDetailDao.saveOrUpdate(scheduleDetail);
            }

            if (fieldCounter == 16) {
                // all fields processed, so reset counter for next record (if there is one)
                fieldCounter = 0;
            }
        }
        return schedule;
    }

    /**
     * This method will sets the values in the schedule detail fields that are associated with the schedule
     * the fields are set to the values from the form
     *
     * @param scheduleDetailDao dao associated with the schedule detail
     * @param scheduleDetail   the schedule detail being added or updated
     * @param fieldValue the field value from the form that is being processed
     * @param fieldNumber  a counter value being used to keep track of what field the value from the form is for
     * @param actionToPerform set to either add or edit
     * @return Schedule the updated schedule and schedule detail information
     */
    private ScheduleDetail addfieldToScheduleDetail(GenericDao<ScheduleDetail> scheduleDetailDao, ScheduleDetail scheduleDetail, String fieldValue, Integer fieldNumber, String actionToPerform) {
        if (fieldNumber == 1) {
            // field number 1 is id
        } else if (fieldNumber == 2) {
            // second field for schedule details form is userName
            scheduleDetail.setUserName(fieldValue);
            scheduleDetail.setUser(getUserByUserName(fieldValue));
        } else if (fieldNumber == 3) {
            // third field for schedule details form is start time 1
            scheduleDetail.setStartTime1(fieldValue);
        } else if (fieldNumber == 4) {
            // fourth field for schedule details form is start time 2
            scheduleDetail.setStartTime2(fieldValue);
        } else if (fieldNumber == 5) {
            // fifth field for schedule details form is start time 3
            scheduleDetail.setStartTime3(fieldValue);
        } else if (fieldNumber == 6) {
            // sixth field for schedule details form is start time 4
            scheduleDetail.setStartTime4(fieldValue);
        } else if (fieldNumber == 7) {
            // seventh field for schedule details form is start time 5
            scheduleDetail.setStartTime5(fieldValue);
        } else if (fieldNumber == 8) {
            // eighth field for schedule details form is start time 6
            scheduleDetail.setStartTime6(fieldValue);
        } else if (fieldNumber == 9) {
            // ninth field for schedule details form is start time 7
            scheduleDetail.setStartTime7(fieldValue);
        } else if (fieldNumber == 10) {
            // tenth field for schedule details form is end time 1
            scheduleDetail.setEndTime1(fieldValue);
        } else if (fieldNumber == 11) {
            // eleventh field for schedule details form is end time 2
            scheduleDetail.setEndTime2(fieldValue);
        } else if (fieldNumber == 12) {
            // twelth field for schedule details form is end time 3
            scheduleDetail.setEndTime3(fieldValue);
        } else if (fieldNumber == 13) {
            // thirteenth field for schedule details form is end time 4
            scheduleDetail.setEndTime4(fieldValue);
        } else if (fieldNumber == 14) {
            // fourteenth field for schedule details form is end time 5
            scheduleDetail.setEndTime5(fieldValue);
        } else if (fieldNumber == 15) {
            // fifteenth field for schedule details form is end time 6
            scheduleDetail.setEndTime6(fieldValue);
        } else if (fieldNumber == 16) {
            // fifteenth field for schedule details form is end time 7
            scheduleDetail.setEndTime7(fieldValue);
        }

        return scheduleDetail;
    }

    /**
     * This method will sets the values in the schedule detail fields that are associated with the schedule
     * the fields are set to the values from the form
     *
     * @param userName the user name vaue for the user (is unique in user table)
     * @return User the user that the user name value is for
     */
    private User getUserByUserName(String userName) {
        GenericDao userDao = new GenericDao(User.class);
        // retrieve qualifying users for the user name value
        List<User> userList = userDao.getByPropertyEqual("userName", userName);
        // set to the first entry in the list returned of qualifying users
        User user = userList.get(0);
        return user;
    }
}
