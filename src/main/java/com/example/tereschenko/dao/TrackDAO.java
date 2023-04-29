package com.example.tereschenko.dao;

import com.example.tereschenko.models.Track;

import java.sql.Connection;
import java.util.List;

public interface TrackDAO {
    void createTrack(Track track, Connection con);
    Track getTrackById(int id,Connection con );
    List<Track> getAllTracks(Connection con);
    void updateTrack(Track track,Connection con);
    void deleteTrack(int id_track,Connection con);
}
