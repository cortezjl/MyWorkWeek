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
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String actionToPerform = (String)request.getParameter("userAction");
        String actionToPerform = request.getParameter("actionToPerform");
        int idToProcess = 0;
        String message = "";
        GenericDao userDao = new GenericDao(User.class);

        System.out.println("In post, userAction=" + actionToPerform);
        if (actionToPerform.equals("add")) {
            User user = new User(request.getParameter("firstName"),request.getParameter("lastName"),
                    request.getParameter("userName"), request.getParameter("password"),
                    LocalDate.parse((request.getParameter("dateOfBirth")), dateTimeFormatter),
                    LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter),
                    LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter));
            // Get list of role values from the form
            String[] roleValues = request.getParameterValues("roleName");
            // Loop through the list of role values and add the role(s) to the user object
            // int loopCounter = 0;
            for (String roleValue : roleValues) {
                System.out.println("ready to add role to user - roleValue=" + roleValue + " for user name " + user.getUserName());
                // Instantiate and create a new Role and add the user object to the Role object
                Role role = new Role(roleValue, user);
                role.setUser_name(user.getUserName());
                //role.setUser(user);
                // add the Role to the set of Roles for the User
                user.addRole(role);
                //loopCounter = loopCounter + 1;
            }
             System.out.println("After adding role(s) to user, User is: " + user);
             idToProcess = userDao.insert(user);
            message = "User has been added";
            User userAdded = (User)userDao.getById(idToProcess);
            System.out.println("After user has been inserted, User is: " + userAdded);
            request.setAttribute("userAction", "edit");
            request.setAttribute("user", userAdded);
        } else if (actionToPerform.equals("edit")) {
            // Set id of User to edit
            idToProcess = Integer.valueOf(request.getParameter("id"));
            // Create user object and populate by retrieving User object values by id
            User user = (User)userDao.getById(idToProcess);
            // If user_name is changed, make sure user name is not changed to a user name value that already exists
            if (!user.getUserName().equals(request.getParameter("userName"))) {
                // TO call new method to check user name value
            }
            // get list of role id's and role values for the user
            String[] ids = request.getParameterValues("roleId");
            String[] roleValues = request.getParameterValues("roleName");
            System.out.println("size of roleName is =" + roleValues.length);
            // loop through the role id's and update the role values for the user
            int loopCounter = 0;
            for (String idForUserRole : ids) {
                String updatedRole = roleValues[loopCounter];
                System.out.println("ready to update role for user to: " + updatedRole);
                updateRole(idForUserRole, updatedRole);
                loopCounter = loopCounter + 1;
            }
            // update the list of roles for for the user
            //Set<Role> setOfRoles= (Set<Role>)roleDao.getByPropertyEqual("userName", user.getUserName());
            //user.setRoles(setOfRoles);
            user = (User)userDao.getById(idToProcess);
            user = setUserValuesFromForm(request, user);
            userDao.saveOrUpdate(user);
            request.setAttribute("userAction", "edit");
            message = "User has been updated";
            System.out.println("user is" + user);
            request.setAttribute("user",(User)userDao.getById(idToProcess));
        }

        // Access the session
        HttpSession session = request.getSession();
        // Add a message from adding or updating the User to the session.
        request.setAttribute("userUpdateMessage", message);
        // Select list of values for Role select field on form
        request.setAttribute("roleList",setRoleSelectOptions());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/userEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);
        String actionToPerform = request.getParameter("userAction");
        System.out.println("In get, userAction=" + actionToPerform);
        if (actionToPerform.equals("edit")) {
            request.setAttribute("user",(User)userDao.getById(Integer.valueOf(request.getParameter("id"))));
            request.setAttribute("userAction", "edit");
        } else if (actionToPerform.equals("add")) {
            request.removeAttribute("user");
            request.setAttribute("userAction", "add");
        }
        // Select list of values for Role select field on form
        request.setAttribute("roleList",setRoleSelectOptions());
        System.out.println("Leaving the get and user action is " + request.getAttribute("userAction"));
        // forward the request to the page to add or edit a User
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userEdit.jsp");
        dispatcher.forward(request, response);
    }

    private List<String> setRoleSelectOptions () {
        // Create list of Roles to display as select list
        List<String> roleList = new ArrayList<String>();
        roleList.add(" ");
        roleList.add("Administrator");
        roleList.add("Back Of House");
        roleList.add("Busser");
        roleList.add("Front Of House");
        roleList.add("Manager");
        return roleList;
    }

    private void  updateRole(String roleId, String newRole) {
        System.out.println("In updateRole method for id " + roleId + " with new role value of " + newRole);
        GenericDao roleDao = new GenericDao(Role.class);
        // Retrieve the Role record to update
        Role roleToUpdate = (Role)roleDao.getById(Integer.parseInt(roleId));
        // Set the updated role value
        roleToUpdate.setRole(newRole);
        // Update the record
        roleDao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = (Role)roleDao.getById(Integer.parseInt(roleId));
        System.out.println("Role for id " + roleId + " is now set to " + retrievedRole.getRole());
    }

    private User setUserValuesFromForm(HttpServletRequest request, User user) {
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        user.setDateOfBirth(LocalDate.parse((request.getParameter("dateOfBirth")), dateTimeFormatter));
        user.setStartDate(LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter));
        user.setEndDate(LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter));
        return user;
    }
}
