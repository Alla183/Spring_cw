package com.example.tereschenko.controllers;

import com.example.tereschenko.configurations.DatabaseConnection;
import com.example.tereschenko.models.Track;
import com.example.tereschenko.services.TrackService;
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
public class TrackController {
    private final TrackService trackService;
    private final DatabaseConnection databaseConnection;
    @Autowired
    public TrackController(TrackService trackService, DatabaseConnection databaseConnection) {
        this.trackService = trackService;
        this.databaseConnection = databaseConnection;
    }
    @GetMapping("/tracks")
    public String getAllTracks(@NotNull Model model) {
        List<Track> tracks = null;
        try {
            tracks = trackService.getAllTracks(databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("tracks", tracks);
        return "tracks";
    }
    @GetMapping("/track/{id}")
    public String getTrackById(@PathVariable int id, @NotNull Model model) {
        Track track = null;
        try {
            track = trackService.getTrackById(id, databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("trackById", track);
        return "track_info";
    }
    @GetMapping("/createTrack")
    public String createTrackForm(Model model) {
        model.addAttribute("track", new Track());
        return "createTrack";
    }

    @PostMapping("/createt")
    public String createAlbum(@ModelAttribute Track track) {
        try {
            trackService.createTrack(track, databaseConnection.getConnection() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/tracks";
    }


    @PostMapping("/update-tracks")
    public String updateTrack(@ModelAttribute("track") Track track, Model model) {
        try {
            trackService.updateTrack(track, databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/tracks";
    }

    @PostMapping("/deletet/{id}")
    public String deleteTrack(@PathVariable int id) {
        try {
            trackService.deleteTrack(id, databaseConnection.getConnection() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/tracks";
    }
}
