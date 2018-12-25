package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.BO.WeatherPlaylistBO;

@RestController
public class WeatherPlaylistController {

    /**
     * Service responsible for retrieving the playlist track names based in the
     * current temperature for a given city name
     *
     * @param cityName to get the current temperature
     * @return playlist track names
     */
    @GetMapping("/playlist/{cityName}")
    public List<String> getPlayListByCityName(@PathVariable String cityName) {
        WeatherPlaylistBO weatcherPlaylistBO = new WeatherPlaylistBO();

        return weatcherPlaylistBO.getPlaylistTrackNamesByCityName(cityName);
    }

    /**
     * Service responsible for retrieving the playlist track names based in the
     * current temperature for given geographic coordinates
     *
     * @param latitude
     * @param longitude
     * @return playlist track names
     */
    @GetMapping("/playlist/{latitude}/{longitude}")
    public List<String> getPlayListByGeoCoordinates(@PathVariable double latitude, @PathVariable double longitude) {
        WeatherPlaylistBO weatcherPlaylistBO = new WeatherPlaylistBO();

        return weatcherPlaylistBO.getPlaylistTrackNamesByGeoCoordinates(latitude, longitude);
    }
}
