package com.example.tereschenko.controllers;

import com.example.tereschenko.configurations.DatabaseConnection;
import com.example.tereschenko.models.Playlist;
import com.example.tereschenko.services.PlaylistService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PlaylistController {

    private final PlaylistService playlistService;
    private final DatabaseConnection databaseConnection;
    @Autowired
    public PlaylistController(PlaylistService playlistService, DatabaseConnection databaseConnection) {
        this.playlistService = playlistService;
        this.databaseConnection = databaseConnection;
    }

    @GetMapping("/")
    public String getAllPlaylist(@NotNull Model model) {
        try {
            List<Playlist> playlists = playlistService.getAllPlaylist(databaseConnection.getConnection());
            model.addAttribute("playlists", playlists);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "playlists";
    }

    @GetMapping("/playlists/{id}")
    public String getPlaylistById(@PathVariable int id, @NotNull Model model) {
        Playlist playlist = null;
        try {
            playlist = playlistService.getPlaylistById(id, databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("playlistById", playlist);
        return "playlist_info";
    }
    @GetMapping("/createpl")
    public String createPlaylistForm(Model model) {
        model.addAttribute("playlist", new Playlist());
        return "createPlaylist";
    }

    @PostMapping("/createp")
    public String createPlaylist(@ModelAttribute Playlist playlist) {
        try {
            playlistService.createPlaylist(playlist, databaseConnection.getConnection() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @PostMapping("/deletepl/{id}")
    public String deletePlaylist(@PathVariable int id) {
        try {
            playlistService.deletePlaylist(id, databaseConnection.getConnection() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
}
