package com.example.tereschenko.dao;

import com.example.tereschenko.models.Playlist;

import java.sql.Connection;
import java.util.List;

public interface PlaylistDAO {

    void createPlaylist(Playlist playlist, Connection conn);

    Playlist getPlaylistById(int id, Connection conn);

    List<Playlist> getAllPlaylists(Connection conn);

    void updatePlaylist(Playlist playlist, Connection conn);

    void deletePlaylist(int id_playlist, Connection conn);
}

