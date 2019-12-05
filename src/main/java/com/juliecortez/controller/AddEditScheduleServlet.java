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
 * @author JCortez
 */
@WebServlet(
        urlPatterns = {"/addEditScheduleServlet"}
)

public class AddEditScheduleServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, MM/dd/yyyy");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // display query string parameters
        logger.info("getQueryString returns: " + request.getQueryString());
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            logger.info("parameter name= " + paramName);
            String paramValue = request.getHeader(paramName);
            logger.info("parameter value= " + paramValue);
        }
        //String actionToPerform = (String)request.getParameter("userAction");
        String actionToPerform = request.getParameter("actionToPerform");
        int idToProcess = 0;
        String message = "";
        GenericDao scheduleDao = new GenericDao(Schedule.class);

        logger.info("In post, userAction=" + actionToPerform);
        if (actionToPerform.equals("add")) {
            Schedule schedule = new Schedule(LocalDate.parse(request.getParameter("startDate")),
                    LocalDate.parse(request.getParameter("endDate")));

        } else if (actionToPerform.equals("edit")) {
            // Set id of Schedule to edit
            idToProcess = Integer.valueOf(request.getParameter("id"));
            // Create Schedule object and populate by retrieving Schedule object values by id
            Schedule schedule = (Schedule)scheduleDao.getById(idToProcess);

            request.setAttribute("userAction", "edit");
            message = "Schedule has been updated";
            logger.info("Schedule is" + schedule);
            request.setAttribute("schedule",(Schedule)scheduleDao.getById(idToProcess));
        }

        // Access the session
        HttpSession session = request.getSession();
        // Add a message from adding or updating the User to the session.
        request.setAttribute("scheduleUpdateMessage", message);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/scheduleAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao scheduleDao = new GenericDao(Schedule.class);
        String actionToPerform = request.getParameter("userAction");
        logger.info("In get, userAction=" + actionToPerform);
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
        logger.info("Leaving the get and user action is " + request.getAttribute("userAction"));
        // forward the request to the page to add or edit a User
        RequestDispatcher dispatcher = request.getRequestDispatcher("/scheduleAddEdit.jsp");
        dispatcher.forward(request, response);
    }

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


    public Schedule setValuesForScheduleToAdd(LocalDate startDate, LocalDate endDate) {
        Schedule scheduleToAdd = new Schedule();
        scheduleToAdd.setStartDate(startDate);
        scheduleToAdd.setEndDate(endDate);
        List<User> activeUsers = getActiveUsersByDateRange(startDate, endDate);
        for (User user : activeUsers) {
            ScheduleDetail scheduleDetail = new ScheduleDetail();
            scheduleDetail.setUserName(user.getUserName());
            scheduleDetail.setUser(user);
            scheduleToAdd.addScheduleDetail(scheduleDetail);
        }
        return scheduleToAdd;
    }


    public List<User> getActiveUsersByDateRange(LocalDate startDate, LocalDate endDate) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        String hql = "select u FROM User u WHERE u.startDate <= :endDate and u.endDate >= :startDate ";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("startDate",startDate);
        query.setParameter("endDate",endDate);
        List<User> users = query.list();

        System.out.println("size of user list returned from hibernate query is: " + query.list().size());

        session.close();
        return users;
    }
}
