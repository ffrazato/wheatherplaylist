package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.weather;

/**
 * Contract for weather providers
 * @author ffrazato
 *
 */
public interface WeatherProvider {
    /**
     * Given a city name, this method retrieves the current temperature in Celsius degrees
     *
     * @param cityName
     * @return current temperature
     */
    public double getCurrentCelsiusTemperatureByCityName(String cityName);
    
    /**
     * Given geographic coordinates, this method retrieves the current temperature in Celsius degrees
     *
     * @param latitude
     * @param longitude
     * @return current temperature
     */
    public double getCurrentCelsiusTemperatureByGeoCoordinates(double latitude, double longitude);
}
