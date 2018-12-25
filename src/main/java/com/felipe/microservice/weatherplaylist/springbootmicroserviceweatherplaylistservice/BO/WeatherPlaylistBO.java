package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.BO;

import java.util.List;

import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.playlist.GenreEnum;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.playlist.PlaylistProvider;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.playlist.SpotifyPlaylistProvider;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.weather.OpenWeatherMapWeatherProvider;
import com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.weather.WeatherProvider;

/**
 * Handle business logic for weather playlist service
 * 
 * @author ffrazato
 */
public class WeatherPlaylistBO {
    public List<String> getPlayListByCityName(String cityName) {
        List<String> playlistTracks = null;

        // get current tempreature for given city
        WeatherProvider owm = OpenWeatherMapWeatherProvider.getInstance();
        double temperature = owm.getCurrentCelsiusTemperatureByCityName(cityName);

        System.out.println("Current temperature on " + cityName + " is: " + temperature);

        // get playlist for chosen genre
        PlaylistProvider spotifyProvider = SpotifyPlaylistProvider.getInstance();

        if (temperature > 30D) {
            // TODO: suggest tracks for party
            playlistTracks = spotifyProvider.getPlaylistTracksByGenres(GenreEnum.PARTY);
        } else if (temperature >= 15D && temperature <= 30D) {
            // TODO: suggest pop music tracks
            playlistTracks = spotifyProvider.getPlaylistTracksByGenres(GenreEnum.POP);
        } else if (temperature >= 10D && temperature <= 14D) {
            // TODO: suggest rock music tracks
            playlistTracks = spotifyProvider.getPlaylistTracksByGenres(GenreEnum.ROCK);
        } else {
            // TODO: suggests classical music tracks
            playlistTracks = spotifyProvider.getPlaylistTracksByGenres(GenreEnum.CLASSICAL);
        }

        return playlistTracks;
    }
}
