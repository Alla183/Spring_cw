package com.example.tereschenko.controllers;

import com.example.tereschenko.configurations.DatabaseConnection;
import com.example.tereschenko.dto.ArtistTrackDTO;
import com.example.tereschenko.models.Artist;
import com.example.tereschenko.services.ArtistService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;
@Controller
public class ArtistController {
    private final ArtistService artistService;
    private final DatabaseConnection databaseConnection;

    public ArtistController(ArtistService artistService, DatabaseConnection databaseConnection) {
        this.artistService = artistService;
        this.databaseConnection = databaseConnection;
    }

    @GetMapping("/artists")
    public String getAllArtists(@NotNull Model model) {
        List<Artist> artists = null;
        try {
            artists = artistService.getAllArtists(databaseConnection.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("artists", artists);
        return "artists";
    }

    @GetMapping("/createar")
    public String createArtistForm(Model model) {
        model.addAttribute("artist", new Artist());
        return "createArtist";
    }

    @PostMapping("/createart")
    public String createArtist(@ModelAttribute Artist artist) {
        try {
            artistService.createArtist(artist, databaseConnection.getConnection() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/artists";
    }

    @GetMapping("/artist_tracks/{id}")
    public String getAlbumTrack(@PathVariable int id, @NotNull Model model) {
        try {
            List<ArtistTrackDTO> artistTrackDTOS = artistService.getArtistTrack(id, databaseConnection.getConnection());
            model.addAttribute("tracklist", artistTrackDTOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "artist_tracks";
    }
}
