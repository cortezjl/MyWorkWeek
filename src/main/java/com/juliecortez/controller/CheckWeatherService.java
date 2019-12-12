package com.juliecortez.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.juliecortez.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openweathermap.WeatherResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * The check weather service.
 */
public class CheckWeatherService extends PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Call weather api service
     *
     * @return the boolean
     * @throws Exception the exception
     */
    public String callWeatherAPI() throws Exception  {
        // Get API key value from the properties object
        String APIKeyValue = properties.getProperty("api.key");
        // Create a new client with ClientBuilder"
        Client client = ClientBuilder.newClient();
        // Set the target api that we want to access with the api key and value of the email to be checked
        WebTarget target =
                client.target("https://api.openweathermap.org/data/2.5/weather?zip=53598,us&units=imperial&APPID=" + APIKeyValue);
        // call the api with a get request and get back a response to be able to check the status
        // Identify the type of request we want from the target and return a string
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        //logger.info(response);
        // Use ObjectMapper from Jackson library to help map the response
        ObjectMapper mapper = new ObjectMapper();
        /// Map the response
        WeatherResponse apiResponse = mapper.readValue(response, WeatherResponse.class);
        String weatherInfo = ("Current weather: " + apiResponse.getMain().getTemp() + " " + apiResponse.getWeather().get(0).getDescription());
        logger.info("response to be passed back from calling weather api is: " + weatherInfo);

        // Set the message response based on the results returned
        return weatherInfo;
    }
}
