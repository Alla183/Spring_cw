package com.example.tereschenko.services;

import com.example.tereschenko.dao.impl.ArtistDAOImpl;
import com.example.tereschenko.dto.ArtistTrackDTO;
import com.example.tereschenko.models.Artist;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class ArtistService {
    private ArtistDAOImpl artistDAOImpl;

    public ArtistService(ArtistDAOImpl artistDAOImpl) {
        this.artistDAOImpl = artistDAOImpl;
    }

    public void createArtist(Artist artist, Connection con){
        artistDAOImpl.createArtist(artist, con);
    }

    public  List<ArtistTrackDTO> getArtistTrack(int id_artist, Connection con){return artistDAOImpl.getArtistTrack(id_artist, con);}

    public List<Artist> getAllArtists(Connection connection){
        return artistDAOImpl.getAllArtists(connection);
    }
}
