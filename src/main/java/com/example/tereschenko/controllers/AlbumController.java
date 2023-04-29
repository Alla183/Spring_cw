package com.example.tereschenko.controllers;

import com.example.tereschenko.configurations.DatabaseConnection;
import com.example.tereschenko.dto.AlbumTrackDTO;
import com.example.tereschenko.models.Album;
import com.example.tereschenko.services.AlbumService;
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
public class AlbumController {

    private final AlbumService albumService;
    private final DatabaseConnection databaseConnection;

    @Autowired
    public AlbumController(AlbumService albumService, DatabaseConnection databaseConnection) {
        this.albumService = albumService;
        this.databaseConnection = databaseConnection;
    }

    @GetMapping("/albums")
    public String getAllAlbums(@NotNull Model model) {
        try {
            List<Album> albums = albumService.getAllAlbums(databaseConnection.getConnection());
            model.addAttribute("albums", albums);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "albums";
    }

    @GetMapping("/albums/{id}")
    public String getAlbumById(@PathVariable int id, @NotNull Model model) {
        try {
            Album album = albumService.getAlbumById(id, databaseConnection.getConnection());
            model.addAttribute("albumById", album);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "albums_info";
    }
    @GetMapping("/create")
    public String createAlbumForm(Model model) {
        model.addAttribute("album", new Album());
        return "createAlbum";
    }

    @PostMapping("/createa")
    public String createAlbum(@ModelAttribute Album album) {
        try {
            albumService.createAlbum(album, databaseConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/albums";
    }

    @PostMapping("/deletea/{id}")
    public String deleteAlbum(@PathVariable int id) {
        try {
            albumService.deleteAlbum(id, databaseConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "redirect:/albums";
    }
    @GetMapping("/album_tracks/{id}")
    public String getAlbumTrack(@PathVariable int id, @NotNull Model model) {
        try {
            List<AlbumTrackDTO> albumTrackDTOS = albumService.getAlbumTrack(id, databaseConnection.getConnection());
            model.addAttribute("tracklist", albumTrackDTOS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "album_tracks";
    }
}
