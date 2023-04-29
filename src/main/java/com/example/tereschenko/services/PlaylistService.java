package com.example.tereschenko.services;

import com.example.tereschenko.dao.impl.PlaylistDAOImpl;
import com.example.tereschenko.models.Playlist;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
@Service
public class PlaylistService {
    private PlaylistDAOImpl playlistDAOImpl;

    public PlaylistService(PlaylistDAOImpl playlistDAOImpl) {
        this.playlistDAOImpl = playlistDAOImpl;
    }

    public List<Playlist> getAllPlaylist(Connection con) {
        return playlistDAOImpl.getAllPlaylists(con);
    }

    public Playlist getPlaylistById(int id, Connection con){
        return playlistDAOImpl.getPlaylistById(id, con);
    }

    public void createPlaylist(Playlist playlist, Connection con){
        playlistDAOImpl.createPlaylist(playlist, con);
    }
    public void deletePlaylist(int id_playlist, Connection con){
        playlistDAOImpl.deletePlaylist(id_playlist, con);
    }
}
