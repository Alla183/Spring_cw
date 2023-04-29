package com.example.tereschenko.dao;

import com.example.tereschenko.dto.ArtistTrackDTO;
import com.example.tereschenko.models.Artist;

import java.sql.Connection;
import java.util.List;

public interface ArtistDAO {

    void createArtist(Artist artist, Connection conn);

    Artist getArtistById(int id, Connection connection);

    List<Artist> getAllArtists(Connection connection);

    List<ArtistTrackDTO> getArtistTrack(int id_artist, Connection con);
    void updateArtist(Artist artist, Connection connection);

    void deleteArtist(Artist artist, Connection connection);
}
