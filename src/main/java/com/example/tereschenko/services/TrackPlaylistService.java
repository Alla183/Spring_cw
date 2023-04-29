package com.example.tereschenko.services;

import com.example.tereschenko.dao.impl.TrackPlaylistDAOImpl;
import com.example.tereschenko.dto.PlaylistInfoDTO;
import com.example.tereschenko.dto.PlaylistTrackDurationDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class TrackPlaylistService {
    private TrackPlaylistDAOImpl trackPlaylistDAOImpl;

    public TrackPlaylistService(TrackPlaylistDAOImpl trackPlaylistDAOImpl) {
        this.trackPlaylistDAOImpl = trackPlaylistDAOImpl;
    }
    public List<PlaylistTrackDurationDTO> getPlaylistTrackDurations(Connection connection){return trackPlaylistDAOImpl.getPlaylistTrackDurations(connection);}
    public List<PlaylistInfoDTO> getPlaylistTrackInfo(int id_playlist, Connection connection){return trackPlaylistDAOImpl.getPlaylistTrackInfo(id_playlist,connection);}
    public void addTrackToPlaylist(int id_track, int id_playlist, Connection conn){trackPlaylistDAOImpl.addTrackToPlaylist(id_track, id_playlist, conn);}

    public void removeTrackFromPlaylist(int id_track, int id_playlist, Connection conn){trackPlaylistDAOImpl.removeTrackFromPlaylist(id_track, id_playlist, conn);}

}
