package com.felipe.microservice.weatherplaylist.springbootmicroserviceweatherplaylistservice.mechanism.provider.playlist;

import java.util.List;

/**
 * Contract for playlist providers
 * @author ffrazato
 *
 */
public interface PlaylistProvider {
    /**
     * Given a playlist genre it retrieves the playlist tracks
     * @param genre
     * @return
     */
    public List<String> getPlaylistTracksByGenres(GenreEnum genre); 
}
