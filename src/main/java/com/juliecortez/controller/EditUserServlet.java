package com.juliecortez.controller;

import com.juliecortez.entity.Role;
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
import java.util.Set;

/**
 * Edit the user
 *
 * @author JCortez
 * sources used: https://www.javaguides.net/2019/03/jsp-servlet-jdbc-mysql-crud-example-tutorial.html
 */
@WebServlet(
        urlPatterns = {"/editUserServlet"}
)

public class EditUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        GenericDao userDao = new GenericDao(User.class);
        String[] ids = request.getParameterValues("roleId");
        String[] roleValues = request.getParameterValues("roleName");
        int loopCounter = 0;
        for (String id : ids) {
            String updatedRole = roleValues[loopCounter];
            updateRole(id, updatedRole);
            loopCounter = loopCounter + 1;
        }

        User user = (User)userDao.getById(Integer.valueOf(request.getParameter("id")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setDateOfBirth(LocalDate.parse((request.getParameter("dateOfBirth")), dateTimeFormatter));
        user.setStartDate(LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter));
        user.setEndDate(LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter));



        userDao.saveOrUpdate(user);
        request.setAttribute("user", user);

        // Access the session
        HttpSession session = request.getSession();
        // Add a message from updating the User to the session.
        request.setAttribute("userUpdateMessage", "User has been updated");


        // Create list of Roles to display as select list
        List<String> roleList = new ArrayList<String>();
        roleList.add(" ");
        roleList.add("Administrator");
        roleList.add("Back Of House");
        roleList.add("Busser");
        roleList.add("Front Of House");
        roleList.add("Manager");
        request.setAttribute("roleList",roleList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userEdit.jsp");
        dispatcher.forward(request, response);

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);
        request.setAttribute("user",(User)userDao.getById(Integer.valueOf(request.getParameter("id"))));

        // Create list of Roles to display as select list
        List<String> roleList = new ArrayList<String>();
        roleList.add(" ");
        roleList.add("Administrator");
        roleList.add("Back Of House");
        roleList.add("Busser");
        roleList.add("Front Of House");
        roleList.add("Manager");
        request.setAttribute("roleList",roleList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userEdit.jsp");
        dispatcher.forward(request, response);
    }

    private void  updateRole(String roleId, String newRole) {
        GenericDao roleDao = new GenericDao(Role.class);
        // Retrieve the Role record to update
        Role roleToUpdate = (Role)roleDao.getById(Integer.parseInt(roleId));
        // Set the updated role value
        roleToUpdate.setRole(newRole);
        // Update the record
        roleDao.saveOrUpdate(roleToUpdate);
    }
}
