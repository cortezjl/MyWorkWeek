package com.juliecortez.controller;


import com.juliecortez.util.PropertiesLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

/**
 *  This servlet will load the weather properties file, which contains an api key
 *  used to get current weather informtion and makes it available to the ServletContext
 *
 *@author    JCortez
 */

@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/myworkweek-startup" },
        loadOnStartup = 1
)

public class ApplicationStartup extends HttpServlet {
    // Declare instance variables
    private Properties properties;
    private ServletContext context;

    /**
     *  The init method will call the other methods to load the properties
     *  file and instantiate an EmployeeDirectory object and load both as
     *  attributes to the ServletContext
     */
    public void init() throws ServletException {

        loadProperties();
        setPropertiesAttribute(properties);
    }

    /**
     *  The loadProjectProperties method will load a project 4 properties config
     *  text file into the Properties object
     */
    public void loadProperties() {
        // Instantiate the Properties object
        properties = new Properties();
        // Load the static properties file
        PropertiesLoader.loadProperties("/weather.properties");
    }

    /**
     *  The setPropertiesAttribute method will set an Attribute in the
     *  ServletContext for the weather properties file
     *  @param properties  A Properties object
     */
    public void setPropertiesAttribute(Properties properties) {
        //Access the ServletContext object
        context = getServletContext();
        // Add the properties object to the ServletContext as an attribute
        context.setAttribute("weatherProperties", properties);
    }
}

