package com.juliecortez.controller;

import com.juliecortez.entity.Role;
import com.juliecortez.entity.TimeOffRequest;
import com.juliecortez.entity.User;
import com.juliecortez.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import java.util.ArrayList;
import java.util.List;


/**
 * Add or Edit a time off request
 *
 * @author JCortez
 */
@WebServlet(
        urlPatterns = {"/addEditTimeOffRequestServlet"}
)

public class AddEditTimeOffRequestServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionToPerform = request.getParameter("actionToPerform");
        int idToProcess = 0;
        String message = "";
        GenericDao timeOffRequestDao = new GenericDao(TimeOffRequest.class);

        System.out.println("In post, actionToPerform=" + actionToPerform);
        if (actionToPerform.equals("add")) {
            TimeOffRequest timeOffRequest = new TimeOffRequest(request.getParameter("userName"),
                    LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter),
                    LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter),
                    request.getParameter("startTime"),
                    request.getParameter("endTime"),
                    getUserByUserName(request.getParameter("userName")));
            idToProcess = timeOffRequestDao.insert(timeOffRequest);
            message = "Time off request has been added";
            TimeOffRequest timeOffRequestAdded = (TimeOffRequest)timeOffRequestDao.getById(idToProcess);
            request.setAttribute("timeOffRequestAction", "edit");
            request.setAttribute("timeOffRequest", timeOffRequestAdded);
        } else if (actionToPerform.equals("edit")) {
            // Set id of time off request to edit
            idToProcess = Integer.valueOf(request.getParameter("id"));
            // Create time off request object and populate by retrieving TimeOffRequest object values by id
            TimeOffRequest timeOffRequest = (TimeOffRequest)timeOffRequestDao.getById(idToProcess);
            // Update fields from values changed on the screen
            timeOffRequest = setTimeOffRequestValuesFromForm(request, timeOffRequest);
            timeOffRequestDao.saveOrUpdate(timeOffRequest);
            request.setAttribute("timeOffRequestAction", "edit");
            message = "Time off request has been updated";
            System.out.println("timeOffRequest is" + timeOffRequest);
            request.setAttribute("timeOffRequest",(TimeOffRequest)timeOffRequestDao.getById(idToProcess));
        }

        // Access the session
        HttpSession session = request.getSession();
        // Add a message from adding or updating the User to the session.
        request.setAttribute("timeOffRequestUpdateMessage", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/timeOffRequestAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao timeOffRequestDao = new GenericDao(TimeOffRequest.class);
        String actionToPerform = request.getParameter("timeOffRequestAction");
        System.out.println("In get, actionToPerform=" + actionToPerform);
        if (actionToPerform.equals("edit")) {
            request.setAttribute("timeOffRequest",(TimeOffRequest)timeOffRequestDao.getById(Integer.valueOf(request.getParameter("id"))));
            request.setAttribute("timeOffRequestAction", "edit");
        } else if (actionToPerform.equals("add")) {
            request.removeAttribute("timeOffRequest");
            request.setAttribute("timeOffRequestAction", "add");
        }
        System.out.println("Leaving the get and time off request action is " + request.getAttribute("timeOffRequestAction"));
        // forward the request to the page to add or edit a time off request
        RequestDispatcher dispatcher = request.getRequestDispatcher("/timeOffRequestAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    private TimeOffRequest setTimeOffRequestValuesFromForm(HttpServletRequest request, TimeOffRequest timeOffRequest) {
        timeOffRequest.setStartDate(LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter));
        timeOffRequest.setEndDate(LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter));
        timeOffRequest.setStartTime(request.getParameter("startTime"));
        timeOffRequest.setEndTime(request.getParameter("endTime"));
        timeOffRequest.setUser(getUserByUserName(request.getParameter("userName")));
        return timeOffRequest;
    }

    private User getUserByUserName(String userName) {
        GenericDao userDao =  new GenericDao(User.class);
        List<User> users = userDao.getByPropertyEqual("userName", userName);
        return users.get(0);
    }
}
