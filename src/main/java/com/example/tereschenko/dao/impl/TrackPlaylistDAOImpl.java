package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.TrackPlaylistDAO;
import com.example.tereschenko.dto.PlaylistInfoDTO;
import com.example.tereschenko.dto.PlaylistTrackDurationDTO;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TrackPlaylistDAOImpl implements TrackPlaylistDAO {
    @Override
    public void addTrackToPlaylist(int id_track, int id_playlist, Connection conn) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO track_playlist (id_track, id_playlist) VALUES (?, ?)");
            preparedStatement.setInt(1, id_track);
            preparedStatement.setInt(2, id_playlist);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTrackFromPlaylist(int id_track, int id_playlist, Connection conn) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM track_playlist WHERE id_track = ? AND id_playlist = ?");
            preparedStatement.setInt(1, id_track);
            preparedStatement.setInt(2, id_playlist);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<PlaylistTrackDurationDTO> getPlaylistTrackDurations(Connection connection) {
        String sql = "SELECT p.id_playlist, p.name AS playlist_name, t.id_track, t.name AS track_name, t.duration " +
                "FROM playlist p " +
                "JOIN track_playlist tp ON p.id_playlist = tp.id_playlist " +
                "JOIN track t ON tp.id_track = t.id_track";
        List<PlaylistTrackDurationDTO> playlistTrackDurations = new ArrayList<>();
        try (Connection conn = connection;
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id_playlist = rs.getInt("id_playlist");
                String playlistName = rs.getString("playlist_name");
                int id_track = rs.getInt("id_track");
                String trackName = rs.getString("track_name");
                String duration = rs.getString("duration");
                PlaylistTrackDurationDTO dto = new PlaylistTrackDurationDTO(id_playlist, playlistName, id_track, trackName, duration);
                playlistTrackDurations.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistTrackDurations;
    }

    @Override
    public List<PlaylistInfoDTO> getPlaylistTrackInfo(int id_playlist, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT t.id_track, t.name, t.duration FROM playlist p JOIN track_playlist tp ON tp.id_playlist = p.id_playlist JOIN track t ON t.id_track = tp.id_track WHERE p.id_playlist = ?;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStatement.setInt(1, id_playlist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<PlaylistInfoDTO> playlistInfoDTOS = new ArrayList<>();
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id_track = rs.getInt("id_track");
                String trackName = rs.getString("name");
                String duration = rs.getString("duration");
                PlaylistInfoDTO dto = new PlaylistInfoDTO(id_track, trackName, duration);
                playlistInfoDTOS.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistInfoDTOS;
    }

}
