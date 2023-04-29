package com.example.tereschenko.dao;

import com.example.tereschenko.models.Genre;

import java.util.List;

public interface GenreDAO {
    void createGenre(Genre genre);
    Genre getGenreById(int id);
    List<Genre> getAllGenres();
    void updateGenre(Genre genre);
    void deleteGenre(Genre genre);
}
