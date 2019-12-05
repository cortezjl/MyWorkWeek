package com.juliecortez.controller;

import com.juliecortez.entity.TimeOffRequest;
import com.juliecortez.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * A servlet used to search for time off requests.
 * @author JCortez
 */

@WebServlet(
        urlPatterns = {"/searchTimeOffRequest"}
)
public class SearchTimeOffRequest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao timeOffRequestDao = new GenericDao(TimeOffRequest.class);
        // get parameters from request
        String searchType = req.getParameter("searchType");
        String searchField = "userName";
        String searchValue = req.getParameter("searchValue");
        // Retrieve time off requests based on search type and set attribute with list of qualifying time off requests
        if (searchType.equals("allTimeOffRequests")) {
            req.setAttribute("timeOffRequests", timeOffRequestDao.getAll());
        } else {
            req.setAttribute("timeOffRequests", timeOffRequestDao.getByPropertyLike(searchField, searchValue));
        }
        // forward the request to jsp to display the search results
        RequestDispatcher dispatcher = req.getRequestDispatcher("timeOffRequestSearchResults.jsp");
        dispatcher.forward(req, resp);
    }
}
