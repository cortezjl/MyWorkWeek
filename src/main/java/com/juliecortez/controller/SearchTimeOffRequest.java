package com.juliecortez.controller;

import com.juliecortez.entity.Schedule;
import com.juliecortez.entity.TimeOffRequest;
import com.juliecortez.persistence.GenericDao;
import com.juliecortez.persistence.SessionFactoryProvider;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * A servlet used to search for time off requests.
 * @author JCortez
 */

@WebServlet(
        urlPatterns = {"/searchTimeOffRequest"}
)
public class SearchTimeOffRequest extends HttpServlet {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao timeOffRequestDao = new GenericDao(TimeOffRequest.class);
        // get parameters from the request
        String searchType = req.getParameter("searchType");
        String searchField = "userName";
        String searchValue = req.getParameter("searchValue");

        // Retrieve time off requests based on search type and set attribute with list of qualifying time off requests
        if (searchType.equals("allTimeOffRequests")) {
            // retrieve all time off requests
            req.setAttribute("timeOffRequests", timeOffRequestDao.getAll());
        } else if (searchType.equals("weeklyRequests")) {
            // retrieve time off requests that fall within the schedule week
            req.setAttribute("timeOffRequests", getRequestsForScheduleWeek(LocalDateTime.parse(searchValue, dateTimeFormatter)));
            req.setAttribute("timeOffRequestsDisplayOnly", "true");
        } else {
            // retrieve time off requests that match by the search field and search value
            req.setAttribute("timeOffRequests", timeOffRequestDao.getByPropertyLike(searchField, searchValue));
        }

        // forward the request to jsp to display the search results
        RequestDispatcher dispatcher = req.getRequestDispatcher("timeOffRequestSearchResults.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     *  This method will retrieve any time off requests that fall in the dates for the weekly schedule
     *
     * @param startDate the starting date of the schedule week
     * @return List<TimeOffRequest> the list of qualifying time off requests to display
     * @author JCortez
     */
    private List<TimeOffRequest> getRequestsForScheduleWeek(LocalDateTime startDate) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        String hql = "select to FROM TimeOffRequest to WHERE to.startDate <= :endDate and to.startDate >= :startDate ";
        Query<TimeOffRequest> query = session.createQuery(hql, TimeOffRequest.class);
        query.setParameter("startDate",startDate);
        query.setParameter("endDate", startDate.plusDays(6).plusHours(23).plusMinutes(59));
        List<TimeOffRequest> timeOffRequests = query.list();
        session.close();
        return timeOffRequests;
    }
}
