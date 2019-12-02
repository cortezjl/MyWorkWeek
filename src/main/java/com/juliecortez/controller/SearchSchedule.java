package com.juliecortez.controller;

import com.juliecortez.entity.Schedule;
import com.juliecortez.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * A servlet used to search for schedules
 * @author JCortez
 */

@WebServlet(
        urlPatterns = {"/searchSchedule"}
)
public class SearchSchedule extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao scheduleDao = new GenericDao(Schedule.class);
        // get parameters from request
        String searchType = req.getParameter("searchType");
        String searchField = "startDate";
        String searchValue = req.getParameter("searchValue");
        if (searchType.equals("allSchedules")) {
            req.setAttribute("schedules", scheduleDao.getAll());
        }
        System.out.println("number of schedule entries=" + scheduleDao.getAll().size());
        System.out.println("schedules=" + req.getAttribute("schedules"));

        //     } else {
   //         req.setAttribute("schedules", scheduleDao.getByPropertyLike(searchField, searchValue));
   //     }
        RequestDispatcher dispatcher = req.getRequestDispatcher("scheduleSearchResults.jsp");
        dispatcher.forward(req, resp);
    }

}
