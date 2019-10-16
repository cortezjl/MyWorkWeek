package com.juliecortez.controller;

import com.juliecortez.entity.User;
import com.juliecortez.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Add a user
 *
 * @author JCortez
 */
@WebServlet(
        urlPatterns = {"/addUserServlet"}
)
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        GenericDao userDao = new GenericDao(User.class);

        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setUserName(req.getParameter("userName"));
        user.setDateOfBirth(LocalDate.parse((req.getParameter("dateOfBirth")), dateTimeFormatter));

        userDao.insert(user);


        RequestDispatcher dispatcher = req.getRequestDispatcher("searchUser?searchTerm=&submit=viewAll");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);
        User user = new User();
        req.setAttribute("user", user);


        // Create list of Roles to display as select list
        List<String> roleList = new ArrayList<String>();
        roleList.add(" ");
        roleList.add("Administrator");
        roleList.add("Back Of House");
        roleList.add("Busser");
        roleList.add("Front Of House");
        roleList.add("Manager");
        req.setAttribute("roleList",roleList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/userAdd.jsp");
        dispatcher.forward(req, resp);
    }
}
