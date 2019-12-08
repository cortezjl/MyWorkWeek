package com.juliecortez.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * This class was modified from the interface given in advanced java.  It now holds two static variables one that is the
 * logger that will be used by most classes and the other is the properties that will be used by most classes
 * @author Eric Knapp
 * @author Alex Malotky
 *
 */
public class PropertiesLoader
{
    protected static final Logger logger = LogManager.getLogger(PropertiesLoader.class);
    public static Properties properties;

    /**
     * Modified from a default method, this method will load a properties file into the static Properties instance.
     * @param propertiesFilePath a path to a file on the java classpath list
     */
    public static void loadProperties(String propertiesFilePath)
    {
        properties = new Properties();
        try
        {
            properties.load(PropertiesLoader.class.getResourceAsStream(propertiesFilePath));

        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }

}
