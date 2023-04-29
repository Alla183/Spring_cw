package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.GenreDAO;
import com.example.tereschenko.models.Genre;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {
    private final Connection conn;

    public GenreDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createGenre(Genre genre) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO genre (id_genre, name) VALUES (?, ?)"
            );
            stmt.setInt(1, genre.getId_genre());
            stmt.setString(2, genre.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * FROM genre WHERE id_genre = ?"
            );
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                genre = new Genre(
                        rs.getInt("id_genre"),
                        rs.getString("name")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genre;
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM genre");
            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getInt("id_genre"),
                        rs.getString("name")
                );
                genres.add(genre);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genres;
    }

    @Override
    public void updateGenre(Genre genre) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE genre SET name = ? WHERE id_genre = ?"
            );
            stmt.setString(1, genre.getName());
            stmt.setInt(2, genre.getId_genre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteGenre(Genre genre) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM genre WHERE id_genre = ?"
            );
            stmt.setInt(1, genre.getId_genre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
