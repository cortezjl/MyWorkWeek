package com.juliecortez.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * A simple servlet whose purpose is to redirect to the home page
 * after a log in attempt
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/loginAction"}
)
public class LoginAction extends HttpServlet {
    private GenericDao genericDao = new GenericDao(User.class);

    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("The logged in user: " + request.getRemoteUser() + " has a role of Administrator: " + request.isUserInRole("Administrator"));
        logger.info("The logged in user: " + request.getRemoteUser() + " has a role of Manager: " + request.isUserInRole("Manager"));
        // get the session from the request
        HttpSession session = request.getSession();

        // get the user name value for the user that is logged in
        String remoteUserId = request.getRemoteUser();

        // Instantiate a generic DAO for the User object and get the user data to display their name on the web page
        User user = (User)genericDao.getByPropertyEqual("userName",remoteUserId).get(0);

        // format the welcome message to display
        String welcomeMessage = "Welcome " + user.getFirstName() + " " + user.getLastName();
        // set the welcome message as a session attribute
        session.setAttribute("welcomeUser", welcomeMessage);

        // forward the request to the home page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, resp);
    }

}
