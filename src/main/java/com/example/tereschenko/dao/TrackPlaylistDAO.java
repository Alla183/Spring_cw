package com.example.tereschenko.dao;

import com.example.tereschenko.dto.PlaylistInfoDTO;
import com.example.tereschenko.dto.PlaylistTrackDurationDTO;

import java.sql.Connection;
import java.util.List;

public interface TrackPlaylistDAO {
    void addTrackToPlaylist(int id_track, int id_playlist, Connection conn);
    void removeTrackFromPlaylist(int id_track, int id_playlist, Connection conn);

    List<PlaylistTrackDurationDTO> getPlaylistTrackDurations(Connection connection);
    List<PlaylistInfoDTO> getPlaylistTrackInfo(int id_playlist, Connection connection);
}