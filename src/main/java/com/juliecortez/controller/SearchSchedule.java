package com.juliecortez.controller;

import com.juliecortez.entity.Schedule;
import com.juliecortez.persistence.GenericDao;
import com.juliecortez.persistence.SessionFactoryProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * A servlet used to search for schedules
 * @author JCortez
 */

@WebServlet(
        urlPatterns = {"/searchSchedule"}
)
public class SearchSchedule extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao scheduleDao = new GenericDao(Schedule.class);
        // get parameters from request
        String searchType = req.getParameter("searchType");
        LocalDate startDate = LocalDate.parse(req.getParameter("startDate"));
        logger.info("searchType = " + searchType + " and startDate = " + startDate);
        List<Schedule> schedules = new ArrayList<Schedule>();
        String destination = "";
        if (searchType.equals("findAllSchedules")) {
            req.setAttribute("schedules", scheduleDao.getAll());
            destination = "scheduleSearchResults.jsp";
            logger.info("ready to do a search for all schedules");
        } else if (searchType.equals("findByStartDate")) {
            logger.info("ready to do get by property equal");
            //schedules = scheduleDao.getByPropertyEqual("startDate", startDate);
            schedules = getScheduleByStartDate(startDate);
            logger.info("size of schedules is: " + schedules.size() );
            if (schedules.size() == 0) {
                // schedule not found, send startDate to use for new schedule
                req.setAttribute("userAction", "add");
                destination = "addEditScheduleServlet?userAction=add";
            } else {
                // schedule was already found, so will find that schedule for the user to edit
                req.setAttribute("userAction", "edit");
                destination = "addEditScheduleServlet?userAction=edit&id=" + schedules.get(0).getId();
            }
            logger.info("ready to do a add or edit of schedule.  The action is: " + req.getAttribute("userAction"));
        } else {
            // we are adding a new schedule, for date entered by the user (searchValue)
            // First make sure that a schedule does not already exist for the date
            //schedules = scheduleDao.getByPropertyEqual("startDate", startDate);
            schedules = getScheduleByStartDate(startDate);
            destination = "addEditScheduleServlet";
            if (schedules.size() == 0) {
                // schedule not found, send startDate to use for new schedule
                req.setAttribute("userAction", "add");
            } else {
                // schedule was already found, so will find that schedule for the user to edit
                req.setAttribute("userAction", "edit");
                req.setAttribute("id", schedules.get(0).getId());
            }
            logger.info("ready to do a add or edit of schedule.  The action is: " + req.getAttribute("userAction"));
        }
        logger.info("number of schedule entries=" + scheduleDao.getAll().size());
        logger.info("schedules=" + req.getAttribute("schedules"));

        //     } else {
   //         req.setAttribute("schedules", scheduleDao.getByPropertyLike(searchField, searchValue));
   //     }
        logger.info("the destination to forward to is: " + destination);
        RequestDispatcher dispatcher = req.getRequestDispatcher(destination);
        dispatcher.forward(req, resp);
    }

    public List<Schedule> getScheduleByStartDate(LocalDate startDate) {
            Session session = SessionFactoryProvider.getSessionFactory().openSession();

        String hql = "select s FROM Schedule s WHERE s.startDate = :startDate ";
        Query<Schedule> query = session.createQuery(hql, Schedule.class);
        query.setParameter("startDate",startDate);
        List<Schedule> schedules = query.list();

        logger.info("size of list returned from hibernate query is: " + query.list().size());

            session.close();
            return schedules;
    }
}
