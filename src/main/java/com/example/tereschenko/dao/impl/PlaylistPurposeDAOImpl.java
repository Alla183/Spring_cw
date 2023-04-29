package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.PlaylistPurposeDAO;
import com.example.tereschenko.models.PlaylistPurpose;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistPurposeDAOImpl implements PlaylistPurposeDAO {

    private final Connection conn;

    public PlaylistPurposeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createPlaylistPurpose(PlaylistPurpose playlistPurpose) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist_purpose (id_playlist, id_purpose) VALUES (?, ?)");
            ps.setInt(1, playlistPurpose.getId_playlist());
            ps.setInt(2, playlistPurpose.getId_purpose());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlaylistPurpose getPlaylistPurposeById(int id_playlist, int id_purpose) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist_purpose WHERE id_playlist = ? AND id_purpose = ?");
            ps.setInt(1, id_playlist);
            ps.setInt(2, id_purpose);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PlaylistPurpose playlistPurpose = new PlaylistPurpose();
                playlistPurpose.setId_playlist(rs.getInt("id_playlist"));
                playlistPurpose.setId_purpose(rs.getInt("id_purpose"));
                return playlistPurpose;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PlaylistPurpose> getAllPlaylistPurposes() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist_purpose");
            ResultSet rs = ps.executeQuery();
            List<PlaylistPurpose> playlistPurposes = new ArrayList<>();
            while (rs.next()) {
                PlaylistPurpose playlistPurpose = new PlaylistPurpose();
                playlistPurpose.setId_playlist(rs.getInt("id_playlist"));
                playlistPurpose.setId_purpose(rs.getInt("id_purpose"));
                playlistPurposes.add(playlistPurpose);
            }
            return playlistPurposes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePlaylistPurpose(PlaylistPurpose playlistPurpose) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE playlist_purpose SET id_purpose = ? WHERE id_playlist = ?");
            ps.setInt(1, playlistPurpose.getId_purpose());
            ps.setInt(2, playlistPurpose.getId_playlist());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePlaylistPurpose(PlaylistPurpose playlistPurpose) {
        try  {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM playlist_purpose WHERE id_playlist = ? AND id_purpose = ?");
            ps.setInt(1, playlistPurpose.getId_playlist());
            ps.setInt(2, playlistPurpose.getId_purpose());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
