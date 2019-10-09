package com.juliecortez.controller;

import com.juliecortez.entity.User;
import com.juliecortez.persistence.GenericDao;

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

/**
 * Edit the user
 *
 * @author JCortez
 */
@WebServlet(
        urlPatterns = {"/editUserServlet"}
)

public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        GenericDao userDao = new GenericDao(User.class);

        // Instantiate User Object
        User user = new User();
        // Set User values from the EditUser Form
        user.setId(Integer.valueOf(req.getParameter("id")));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setUserName(req.getParameter("userName"));
        user.setDateOfBirth(LocalDate.parse((req.getParameter("dateOfBirth")), dateTimeFormatter));
        //user.setRoles(req.getParameter("roles"));

        // TODO Update roles values
        userDao.saveOrUpdate(user);
        req.setAttribute("user", user);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userEdit.jsp");
        dispatcher.forward(req, resp);

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);
        req.setAttribute("user", (User)userDao.getById(Integer.valueOf(req.getParameter("id"))));

        // Access the session
        HttpSession session = req.getSession();
        // Add a message from updating the User to the session.
        session.setAttribute("userUpdateMessage", "User has been updated");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userEdit.jsp");
        dispatcher.forward(req, resp);
    }
}
