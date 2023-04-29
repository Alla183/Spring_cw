package com.example.tereschenko.services;

import com.example.tereschenko.dao.impl.TrackDAOImpl;
import com.example.tereschenko.models.Track;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class TrackService {
    private TrackDAOImpl trackDAOimpl;

    public TrackService(TrackDAOImpl trackDAOimpl) {
        this.trackDAOimpl = trackDAOimpl;
    }

    public List<Track> getAllTracks(Connection con){
        return trackDAOimpl.getAllTracks(con);
    }

    public Track getTrackById(int id,Connection con ){
        return trackDAOimpl.getTrackById(id, con);
    }

    public void createTrack(Track track, Connection con){
        trackDAOimpl.createTrack(track, con);
    }

    public void deleteTrack(int id_track,Connection con){trackDAOimpl.deleteTrack(id_track, con);}

    public void updateTrack(Track track,Connection con){trackDAOimpl.updateTrack(track, con);}
}
