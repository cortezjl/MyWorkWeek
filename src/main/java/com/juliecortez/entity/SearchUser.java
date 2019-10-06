package com.juliecortez.entity;

import com.juliecortez.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * A servlet used to search for users.
 * @author JCortez
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        // get parameters from request
        String searchType = req.getParameter("searchType");
        String searchField = req.getParameter("searchField");
        String searchValue = req.getParameter("searchValue");
        if (searchType.equals("allUsers")) {
            req.setAttribute("users", userDao.getAll());
        } else if (searchType.equals("activeUsers")) {
            req.setAttribute("users", userDao.getAll());
            // TODO change for query that gets only active users

        } else {
            req.setAttribute("users", userDao.getByPropertyLike(searchField, searchValue));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/userSearchResults.jsp");
        dispatcher.forward(req, resp);
    }

}