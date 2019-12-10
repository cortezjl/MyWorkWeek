package com.juliecortez.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliecortez.entity.TimeOffRequest;
import com.juliecortez.entity.User;
import com.juliecortez.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openweathermap.WeatherResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;


/**
 *  This servlet is user to Add or Edit a time off request
 *
 * @author JCortez
 */
@WebServlet(
        urlPatterns = {"/addEditTimeOffRequestServlet"}
)

public class AddEditTimeOffRequestServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionToPerform = request.getParameter("actionToPerform");
        // logger.info("In post, action to perform is= " + actionToPerform);
        int idToProcess = 0;
        String message = "";
        GenericDao timeOffRequestDao = new GenericDao(TimeOffRequest.class);

        if (actionToPerform.equals("add")) {
            // Add time off request for the user, first make sure username is a valid user
            List<User> userList = getUserByUserName(request.getParameter("userName"));
            if (userList.size() == 0) {
                // user name is not found, so send request info back for user to correct
                User user = null;
                TimeOffRequest timeOffRequest = new TimeOffRequest(request.getParameter("userName"),
                        LocalDateTime.parse((request.getParameter("startDate"))),
                        LocalDateTime.parse((request.getParameter("endDate"))),
                        user);
                message = "Username is not a valid user";
                request.setAttribute("timeOffRequest",(TimeOffRequest)timeOffRequestDao.getById(Integer.valueOf(request.getParameter("id"))));
                request.setAttribute("timeOffRequestAction", "add");
            } else {
                // user name is valid, can add the time off request
                logger.info("startDate value is: " + request.getParameter("startDate"));
                TimeOffRequest timeOffRequest = new TimeOffRequest(request.getParameter("userName"),
                        LocalDateTime.parse((request.getParameter("startDate"))),
                        LocalDateTime.parse((request.getParameter("endDate"))),
                        userList.get(0));
                logger.info("ready to insert");
                idToProcess = timeOffRequestDao.insert(timeOffRequest);
                message = "Time off request has been added";
                TimeOffRequest timeOffRequestAdded = (TimeOffRequest) timeOffRequestDao.getById(idToProcess);
                request.setAttribute("timeOffRequestAction", "edit");
                request.setAttribute("timeOffRequest", timeOffRequestAdded);
            }
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
            request.setAttribute("timeOffRequest",(TimeOffRequest)timeOffRequestDao.getById(idToProcess));
        }
        // Access the session
        HttpSession session = request.getSession();
        // Add a message from adding or updating the User to the session.
        request.setAttribute("timeOffRequestUpdateMessage", message);
        // forward the request to the page to add or edit a time off request
        RequestDispatcher dispatcher = request.getRequestDispatcher("/timeOffRequestAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao timeOffRequestDao = new GenericDao(TimeOffRequest.class);
        String actionToPerform = request.getParameter("timeOffRequestAction");
        // set request attributes based on the action to perform (add or edit)
        logger.info("In get, actionToPerform=" + actionToPerform);
        if (actionToPerform.equals("edit")) {
            request.setAttribute("timeOffRequest",timeOffRequestDao.getById(Integer.valueOf(request.getParameter("id"))));
            request.setAttribute("timeOffRequestAction", "edit");
        } else if (actionToPerform.equals("add")) {
            request.removeAttribute("timeOffRequest");
            request.setAttribute("timeOffRequestAction", "add");
        }

        // if the currentWeather attribute does not exist for the session then call the weather api and set the attribute
        HttpSession session = request.getSession();
        if (session.getAttribute ("currentWeather") == null) {
            session.setAttribute("currentWeather", getCurrentWeather());
            System.out.println("setting current weather attribute as: " + session.getAttribute("currentWeather"));
        }

        // forward the request to the page to add or edit a time off request
        RequestDispatcher dispatcher = request.getRequestDispatcher("/timeOffRequestAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * set the starting and ending date/time for the time off request with the values the user entered on the form
     * @param request  the http request
     * @param timeOffRequest the time off request object to be updated
     * @return the updated time off request object
     */
    private TimeOffRequest setTimeOffRequestValuesFromForm(HttpServletRequest request, TimeOffRequest timeOffRequest) {
        timeOffRequest.setStartDate(LocalDateTime.parse(request.getParameter("startDate")));
        timeOffRequest.setEndDate(LocalDateTime.parse((request.getParameter("endDate"))));
        return timeOffRequest;
    }

    /**
     * Retrieve a user by user name
     * @param userName user name value of the user
     * @return User object that matched by user name
     */
    private List<User> getUserByUserName(String userName) {
        GenericDao userDao =  new GenericDao(User.class);
        List<User> users = userDao.getByPropertyEqual("userName", userName);
        return users;
    }

    /**
     * Call the api to retrieve the current weather for display on the screen.
     *
     * @return the current temperature and description of the weather conditions
     */
    private String getCurrentWeather() throws JsonProcessingException {
        // Create a new client with ClientBuilder
        Client client = ClientBuilder.newClient();
        String apiKey = "cc1f07102b573a5e4a1844ae499f557c";
        // Set the target api that we want to access
        WebTarget target =
                client.target("https://api.openweathermap.org/data/2.5/weather?zip=53598,us&units=imperial&APPID=" + apiKey);
        // Identify the type of request we want from the target and return a string
        String apiResponse = target.request(MediaType.APPLICATION_JSON).get(String.class);
        //logger.info("api response= " + apiResponse);
        // Use ObjectMapper from Jackson library to help map the response
        ObjectMapper mapper = new ObjectMapper();
        /// Map the response
        WeatherResponse weatherResponse = mapper.readValue(apiResponse, WeatherResponse.class);
        // Put together the string to display on the screen with the temperature and description of current weather condition
        String weatherInfo = ("Current weather:  " + weatherResponse.getMain().getTemp() + "\u00B0" +  "F " + weatherResponse.getWeather().get(0).getDescription());
        //logger.info("current weather is: " + weatherInfo);

        return weatherInfo;
    }
}
