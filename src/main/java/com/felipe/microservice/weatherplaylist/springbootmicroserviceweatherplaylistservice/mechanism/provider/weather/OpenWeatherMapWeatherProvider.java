package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.weather;

import org.apache.commons.lang3.StringUtils;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.CurrentWeather;

/**
 * Concrete provider for Open Weather Map
 *
 * @author ffrazato
 */
public class OpenWeatherMapWeatherProvider implements WeatherProvider {

    /**
     * Singleton instance for this class
     */
    private static volatile OpenWeatherMapWeatherProvider instance;

    // open weather map API instance
    private OWM owm;

    /**
     * Initiate the class with the API Key
     */
    private OpenWeatherMapWeatherProvider() {
        // Initialize API instance with the api key
        owm = new OWM(getAPIKey());
        // set metric to always retrieve temperatures in Celsius degree
        owm.setUnit(Unit.METRIC);
    }

    @Override
    public double getCurrentCelsiusTemperatureByCityName(String cityname) {
        CurrentWeather cwd = null;

        if (!StringUtils.isEmpty(cityname) && !StringUtils.isBlank(cityname)) {
            try {
                cwd = owm.currentWeatherByCityName(cityname);
            } catch (APIException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            // TODO Felipe: Throw an NullCityNameException
        }

        return extractCurrentTemperature(cwd);
    }

    @Override
    public double getCurrentCelsiusTemperatureByGeoCoordinates(double latitude, double longitude) {
        CurrentWeather cwd = null;

        try {
            cwd = owm.currentWeatherByCoords(latitude, longitude);
        } catch (APIException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return extractCurrentTemperature(cwd);
    }

    /**
     * Extract the current temperature retrieve from open wheater map API
     * 
     * @param currentCelsiusTemperature
     * @param cwd
     * @return current temperature
     */
    private double extractCurrentTemperature(CurrentWeather cwd) {
        double currentCelsiusTemperature = 0D;
        // checking data retrieval was successful or not
        if (cwd != null && cwd.hasRespCode() && cwd.getRespCode() == 200) {
            // checking if max. temp. and min. temp. is available
            if (cwd.hasMainData() && cwd.getMainData().hasTempMax() && cwd.getMainData().hasTempMin()) {
                currentCelsiusTemperature = cwd.getMainData().getTemp();
            }
        }
        return currentCelsiusTemperature;
    }

    /**
     * Retrieve the API Key for open weather map service
     * 
     * @return api key
     */
    private static String getAPIKey() {
        return "c6123c1ef8f3229c4ed7a1848107594d";
    }

    /**
     * Singleton method to get the instance of OpenWeatherMapWeatherProvider
     *
     * @return singleton instance for OpenWeatherMapWeatherProvider
     */
    public static OpenWeatherMapWeatherProvider getInstance() {
        // double check to avoid synchronizing it
        if (instance == null) {
            synchronized (OpenWeatherMapWeatherProvider.class) {
                if (instance == null) {
                    instance = new OpenWeatherMapWeatherProvider();
                }
            }
        }

        return instance;
    }
}
