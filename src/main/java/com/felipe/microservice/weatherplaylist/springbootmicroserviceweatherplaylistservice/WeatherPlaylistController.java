package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.BO.WeatherPlaylistBO;

@RestController
public class WeatherPlaylistController {
    
    @GetMapping("/playlist/by/{cityName}")
    public List<String> getPlayListByCityName(@PathVariable String cityName) {
       WeatherPlaylistBO weatcherPlaylistBO = new WeatherPlaylistBO();
       
       return weatcherPlaylistBO.getPlayListByCityName(cityName);
    }
}
