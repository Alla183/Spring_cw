package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.PlaylistDAO;
import com.example.tereschenko.models.Playlist;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PlaylistDAOImpl implements PlaylistDAO {
    @Override
    public void createPlaylist(Playlist playlist, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO playlist (name) VALUES (?)");
            ps.setString(1, playlist.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Playlist getPlaylistById(int id, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist WHERE id_playlist = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId_playlist(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
                return playlist;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Playlist> getAllPlaylists(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM playlist");
            ResultSet rs = ps.executeQuery();
            List<Playlist> playlists = new ArrayList<>();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId_playlist(rs.getInt("id_playlist"));
                playlist.setName(rs.getString("name"));
                playlists.add(playlist);
            }
            return playlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePlaylist(Playlist playlist, Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE playlist SET name = ? WHERE id_playlist = ?");
            ps.setString(1, playlist.getName());
            ps.setInt(2, playlist.getId_playlist());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePlaylist(int id_playlist, Connection conn) {
        try  {
            PreparedStatement ps0 = conn.prepareStatement("DELETE FROM track_playlist WHERE id_playlist = ?");
            ps0.setInt(1, id_playlist);
            ps0.executeUpdate();
            PreparedStatement ps1 = conn.prepareStatement("DELETE FROM playlist WHERE id_playlist = ?");
            ps1.setInt(1, id_playlist);
            ps1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
