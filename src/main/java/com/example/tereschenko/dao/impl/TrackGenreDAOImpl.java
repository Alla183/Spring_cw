package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.TrackGenreDAO;
import com.example.tereschenko.models.TrackGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackGenreDAOImpl implements TrackGenreDAO {
    private final Connection conn;

    public TrackGenreDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createTrackGenre(TrackGenre trackGenre) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO track_genre (id_track, id_genre) VALUES (?, ?)");
            ps.setInt(1, trackGenre.getId_track());
            ps.setInt(2, trackGenre.getId_genre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TrackGenre> getAllTrackGenres() {
        List<TrackGenre> trackGenres = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_genre");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackGenre trackGenre = new TrackGenre();
                trackGenre.setId_track(rs.getInt("id_track"));
                trackGenre.setId_genre(rs.getInt("id_genre"));
                trackGenres.add(trackGenre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackGenres;
    }

    @Override
    public List<TrackGenre> getTrackGenresByTrackId(int id_track) {
        List<TrackGenre> trackGenres = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_genre WHERE id_track=?");
            ps.setInt(1, id_track);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackGenre trackGenre = new TrackGenre();
                trackGenre.setId_track(rs.getInt("id_track"));
                trackGenre.setId_genre(rs.getInt("id_genre"));
                trackGenres.add(trackGenre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackGenres;
    }

    @Override
    public List<TrackGenre> getTrackGenresByGenreId(int id_genre) {
        List<TrackGenre> trackGenres = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_genre WHERE id_genre=?");
            ps.setInt(1, id_genre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackGenre trackGenre = new TrackGenre();
                trackGenre.setId_track(rs.getInt("id_track"));
                trackGenre.setId_genre(rs.getInt("id_genre"));
                trackGenres.add(trackGenre);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackGenres;
    }

    @Override
    public void deleteTrackGenreByTrackId(int id_track) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM track_genre WHERE id_track=?");
            ps.setInt(1, id_track);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTrackGenreByGenreId(int id_genre) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM track_genre WHERE id_genre=?");
            ps.setInt(1, id_genre);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
