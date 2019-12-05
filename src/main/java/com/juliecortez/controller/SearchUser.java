package com.juliecortez.controller;

import com.juliecortez.entity.User;
import com.juliecortez.persistence.GenericDao;
import com.juliecortez.persistence.SessionFactoryProvider;
import com.juliecortez.persistence.UserDao;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        GenericDao userDao = new GenericDao(User.class);
        // get parameters from request
        String searchType = req.getParameter("searchType");
        String searchField = req.getParameter("searchField");
        String searchValue = req.getParameter("searchValue");
        if (searchType.equals("allUsers")) {
            req.setAttribute("users", userDao.getAll());
        } else if (searchType.equals("activeUsers")) {
            req.setAttribute("users", getUsersActiveToday());
        } else {
            req.setAttribute("users", userDao.getByPropertyLike(searchField, searchValue));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("userSearchResults.jsp");
        dispatcher.forward(req, resp);
    }


    public List<User> getUsersActiveToday() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        String hql = "select u FROM User u WHERE u.startDate <= :endDate and u.endDate >= :startDate ";
        Query<User> query = session.createQuery(hql, User.class);
        LocalDate today = LocalDate.parse(LocalDate.now().format(formatter));
        query.setParameter("startDate",today);
        query.setParameter("endDate",today);
        List<User> users = query.list();

        System.out.println("size of user list returned from hibernate query is: " + query.list().size());

        session.close();
        return users;
    }
}