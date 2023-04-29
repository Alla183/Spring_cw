package com.example.tereschenko.controllers;

import com.example.tereschenko.configurations.DatabaseConnection;
import com.example.tereschenko.dto.PlaylistInfoDTO;
import com.example.tereschenko.dto.PlaylistTrackDurationDTO;
import com.example.tereschenko.services.TrackPlaylistService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class TrackPlaylistController {
    private final TrackPlaylistService trackPlaylistService;
    private final DatabaseConnection databaseConnection;
    @Autowired
    public TrackPlaylistController(TrackPlaylistService trackPlaylistService, DatabaseConnection databaseConnection) {
        this.trackPlaylistService = trackPlaylistService;
        this.databaseConnection = databaseConnection;
    }
    @PostMapping("/playlists/add-track")
    public String addTrackToPlaylist(@RequestParam int id_track, @RequestParam int id_playlist) {
        try {
            trackPlaylistService.addTrackToPlaylist(id_track, id_playlist, databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/allTrackPlaylist")
    public String getAllPlaylist(@NotNull Model model) {
        List<PlaylistTrackDurationDTO> list = null;
        try {
            list = trackPlaylistService.getPlaylistTrackDurations(databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("list", list);
        return "trackPlaylist";
    }

    @GetMapping("/playlist_info/{id}")
    public String getAlbumById(@PathVariable int id, @NotNull Model model) {
        try {
            List<PlaylistInfoDTO> playlistInfoDTOS = trackPlaylistService.getPlaylistTrackInfo(id, databaseConnection.getConnection());
            model.addAttribute("tracklist", playlistInfoDTOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "playlisttrack_info";
    }


}
