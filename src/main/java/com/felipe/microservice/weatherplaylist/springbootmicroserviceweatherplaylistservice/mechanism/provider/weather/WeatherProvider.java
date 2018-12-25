package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.weather;

/**
 * Contract for weather providers
 * @author ffrazato
 *
 */
public interface WeatherProvider {
    /**
     * Given a city name, this method retrieves the current temperature in Celsius
     *
     * @param cityName
     */
    public double getCurrentCelsiusTemperatureByCityName(String cityName);
}
