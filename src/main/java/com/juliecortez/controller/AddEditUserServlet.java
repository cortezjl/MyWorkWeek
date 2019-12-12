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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This servlet is used to add or edit a user and add or edit any roles associated with the user
 * @author JCortez
 */
@WebServlet(
    urlPatterns = {"/addEditUserServlet"}
)

public class AddEditUserServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // retrieve parameter of action to perform (add or edit)
        String actionToPerform = request.getParameter("actionToPerform");
        logger.info("In post, userAction=" + actionToPerform);
        System.out.println("additionalRoleSelected= " + request.getParameter("additionalRoleSelected"));
        int idToProcess = 0;
        String message = "";
        List<User> users = null;
                // set value of addtional role to add from modal - this is a TODO yet for adding additional roles to the user
        //String additionalRole = request.getParameter("roleValueToAdd");
        GenericDao userDao = new GenericDao(User.class);

        if (actionToPerform.equals("add")) {
            users = getUserByUserName(request.getParameter("userName"));
            if (users.size() > 0) {
                // the user name already exists, return the information back to the user to correct
                User user = new User(request.getParameter("firstName"), request.getParameter("lastName"),
                        request.getParameter("userName"), request.getParameter("password"),
                        LocalDate.parse((request.getParameter("dateOfBirth")), dateTimeFormatter),
                        LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter),
                        LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter));
                // set attribute values for the request
                request.setAttribute("user", user);
                request.setAttribute("userAction", "add");
                // Select list of values for Role select field on form for add
                request.setAttribute("roleListAdd",setRoleSelectOptions());
                message =  "Username value already exists, change username";
            } else {
                // the user name does not exist already, add the user
                User user = new User(request.getParameter("firstName"), request.getParameter("lastName"),
                        request.getParameter("userName"), request.getParameter("password"),
                        LocalDate.parse((request.getParameter("dateOfBirth")), dateTimeFormatter),
                        LocalDate.parse((request.getParameter("startDate")), dateTimeFormatter),
                        LocalDate.parse((request.getParameter("endDate")), dateTimeFormatter));
                // Get list of role values from the form
                String[] roleValues = request.getParameterValues("roleNameAdd");
                // Loop through the list of role values and add the role(s) to the user object
                for (String roleValue : roleValues) {
                    logger.info("ready to add role to user - roleValue=" + roleValue + " for user name " + user.getUserName());
                    // Instantiate and create a new Role and add the user object to the Role object
                    Role role = new Role(roleValue, user);
                    role.setUser_name(user.getUserName());
                    // add the Role to the set of Roles for the User
                    user.addRole(role);
                }
                logger.info("After adding role(s) to user, User is: " + user);
                // add the user
                idToProcess = userDao.insert(user);
                message = "User has been added";
                User userAdded = (User) userDao.getById(idToProcess);
                //logger.info("After user has been inserted, User is: " + userAdded);
                // set attribute values for the request
                request.setAttribute("userAction", "edit");
                request.setAttribute("user", userAdded);
                // Select list of values for Role select field on form, use same values as edit now rather than as add
                request.setAttribute("roleList",setRoleSelectOptions());
            }
        } else if (actionToPerform.equals("edit")) {
            // Set id of User to edit
            idToProcess = Integer.valueOf(request.getParameter("id"));
            // Create user object and populate by retrieving User object values by id
            User user = (User)userDao.getById(idToProcess);
            // If user_name is changed, make sure user name is not changed to a user name value that already exists
            if (!user.getUserName().equals(request.getParameter("userName"))) {
                // user name has changed, so make sure new user name value does not exist already
                users = getUserByUserName(request.getParameter("userName"));
            }
            if (users != null) {
                // if any entries in the list of users, then the new user name value already exists, set message and do not perform update
                user = setUserValuesFromForm(request, user);
                message =  "Username value already exists, change username";
                request.setAttribute("user",user);
            } else {
                // get list of role id's and role values for the user
                String[] ids = request.getParameterValues("roleId");
                String[] roleValues = request.getParameterValues("roleName");
                logger.info("size of roleName is =" + roleValues.length);
                // loop through the role id's and update the role values for the user
                int loopCounter = 0;
                for (String idForUserRole : ids) {
                    String updatedRole = roleValues[loopCounter];
                    logger.info("ready to update role for user to: " + updatedRole);
                    updateRole(idForUserRole, updatedRole);
                    loopCounter = loopCounter + 1;
                }
                // update the list of roles for for the user
                //Set<Role> setOfRoles= (Set<Role>)roleDao.getByPropertyEqual("userName", user.getUserName());
                //user.setRoles(setOfRoles);
                user = (User) userDao.getById(idToProcess);
                user = setUserValuesFromForm(request, user);
                userDao.saveOrUpdate(user);
                request.setAttribute("user",(User)userDao.getById(idToProcess));
                message = "User has been updated";
                logger.info("updated user is" + user);
            }
            request.setAttribute("userAction", "edit");
            // Select list of values for Role select field on form
            request.setAttribute("roleList",setRoleSelectOptions());
        }

        // Add a message from adding or updating the User to the session.
        request.setAttribute("userUpdateMessage", message);
        // forward the request to page to add or edit a user
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GenericDao userDao = new GenericDao(User.class);
        // retrieve action chosen by user - either add or edit
        String actionToPerform = request.getParameter("userAction");
        //logger.info("In get, userAction=" + actionToPerform);

        // set request attributes based on users action (add or edit)
        if (actionToPerform.equals("edit")) {
            request.setAttribute("user",(User)userDao.getById(Integer.valueOf(request.getParameter("id"))));
            request.setAttribute("userAction", "edit");
            // Select list of values for Role select field on form for edit
            request.setAttribute("roleList",setRoleSelectOptions());
        } else if (actionToPerform.equals("add")) {
            request.removeAttribute("user");
            request.setAttribute("userAction", "add");
            // Select list of values for Role select field on form for add
            request.setAttribute("roleListAdd",setRoleSelectOptions());
        }

        //logger.info("Leaving the get and user action is " + request.getAttribute("userAction"));
        // forward the request to the page to add or edit a User
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userAddEdit.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * set the list of drop down values for the role field
     * @return list of role names
     */
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

    /**
     * update a role
     */
    private void  updateRole(String roleId, String newRole) {
        logger.info("In updateRole method for id " + roleId + " with new role value of " + newRole);
        GenericDao roleDao = new GenericDao(Role.class);
        // Retrieve the Role record to update
        Role roleToUpdate = (Role)roleDao.getById(Integer.parseInt(roleId));
        // Set the updated role value
        roleToUpdate.setRole(newRole);
        // Update the record
        roleDao.saveOrUpdate(roleToUpdate);
        Role retrievedRole = (Role)roleDao.getById(Integer.parseInt(roleId));
        logger.info("Role for id " + roleId + " is now set to " + retrievedRole.getRole());
    }

    /**
     * set user values from values entered by the user on the form
     * @param request  http request
     * @param user  the user object to add or edit
     * @return user object with updated values form the form
     */
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

    /**
     * retrieve the users by user name.  user name is a unique key, so will be either 1 or none found.
     * @param userName  user name value
     * @return list of users that matched by user name (either one or none)
     */
    private List<User> getUserByUserName(String userName) {
        GenericDao checkUserDao = new GenericDao(User.class);
        List<User> qualifyingUsers = checkUserDao.getByPropertyEqual("userName", userName);
        return qualifyingUsers;
    }
}
