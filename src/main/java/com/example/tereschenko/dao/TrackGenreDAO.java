package com.example.tereschenko.dao;

import com.example.tereschenko.models.TrackGenre;

import java.util.List;

public interface TrackGenreDAO {
    void createTrackGenre(TrackGenre trackGenre);
    List<TrackGenre> getAllTrackGenres();
    List<TrackGenre> getTrackGenresByTrackId(int id_track);
    List<TrackGenre> getTrackGenresByGenreId(int id_genre);
    void deleteTrackGenreByTrackId(int id_track);
    void deleteTrackGenreByGenreId(int id_genre);
}
