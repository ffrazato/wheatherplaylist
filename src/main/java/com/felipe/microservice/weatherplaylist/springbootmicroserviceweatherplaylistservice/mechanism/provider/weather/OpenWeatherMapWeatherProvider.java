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

    private OWM owm;

    /**
     * Initiate the class with the API Key
     */
    private OpenWeatherMapWeatherProvider() {
        // Initializa API instance with the api key
        owm = new OWM(getAPIKey());
    }

    @Override
    public double getCurrentCelsiusTemperatureByCityName(String cityname) {

        double currentCelsiusTemperature = 0D;

        if (!StringUtils.isEmpty(cityname) && !StringUtils.isBlank(cityname)) {
            CurrentWeather cwd = null;
            
            try {
                owm.setUnit(Unit.STANDARD);
                cwd = owm.currentWeatherByCityName(cityname);
            } catch (APIException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // checking data retrieval was successful or not
            if (cwd.hasRespCode() && cwd.getRespCode() == 200) {
                // checking if max. temp. and min. temp. is available
                if (cwd.hasMainData() && cwd.getMainData().hasTempMax() && cwd.getMainData().hasTempMin()) {
                    currentCelsiusTemperature = fromKelvintoCelsius(cwd.getMainData().getTemp());
                }
            }
        }
        else {
            // TODO Felipe: Throw an NullCityNameException
        }
        
        return currentCelsiusTemperature;
    }

    /**
     * Kelvin to Degree Celsius Conversion
     *
     * @param kelvinTemperature
     * @return
     */
    private double fromKelvintoCelsius(double kelvinTemperature) {
        System.out.print("temperature before conversion = " + kelvinTemperature);
        return kelvinTemperature - 273.15D;
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
        if(instance == null) {
            synchronized (OpenWeatherMapWeatherProvider.class) {
                if(instance == null) {
                    instance = new OpenWeatherMapWeatherProvider();
                }
            }
        }
        
        return instance;
    }

}
