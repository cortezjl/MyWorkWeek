package com.juliecortez.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A simple servlet whose purpose is to end the session for the user and remove the welcome message attribute for the user
 * @author JCortez
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get session from the request
        HttpSession session = request.getSession();
        // remove attribute for user that is displayed on the navigation bar
        session.removeAttribute( "remoteUser" );
        // invalidate the session for the user
        session.invalidate();
        // sent user back to home page
        response.sendRedirect("index.jsp");
    }
}