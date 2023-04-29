package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.TrackLabelDAO;
import com.example.tereschenko.models.TrackLabel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackLabelDAOImpl implements TrackLabelDAO {
    private final Connection conn;

    public TrackLabelDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createTrackLabel(TrackLabel trackLabel) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO track_label (id_record_label, id_track, id_artist, year_edition) VALUES (?, ?, ?, ?)");
            ps.setInt(1, trackLabel.getId_record_label());
            ps.setInt(2, trackLabel.getId_track());
            ps.setInt(3, trackLabel.getId_artist());
            ps.setInt(4, trackLabel.getYear_edition());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TrackLabel> getAllTrackLabels() {
        List<TrackLabel> trackLabels = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM track_label");
            while (rs.next()) {
                TrackLabel trackLabel = new TrackLabel();
                trackLabel.setId_record_label(rs.getInt("id_record_label"));
                trackLabel.setId_track(rs.getInt("id_track"));
                trackLabel.setId_artist(rs.getInt("id_artist"));
                trackLabel.setYear_edition(rs.getInt("year_edition"));
                trackLabels.add(trackLabel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackLabels;
    }

    @Override
    public List<TrackLabel> getTrackLabelsByRecordLabelId(int id_record_label) {
        List<TrackLabel> trackLabels = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_label WHERE id_record_label = ?");
            ps.setInt(1, id_record_label);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackLabel trackLabel = new TrackLabel();
                trackLabel.setId_record_label(rs.getInt("id_record_label"));
                trackLabel.setId_track(rs.getInt("id_track"));
                trackLabel.setId_artist(rs.getInt("id_artist"));
                trackLabel.setYear_edition(rs.getInt("year_edition"));
                trackLabels.add(trackLabel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackLabels;
    }

    @Override
    public List<TrackLabel> getTrackLabelsByTrackId(int id_track) {
        List<TrackLabel> trackLabels = new ArrayList<>();
        String sql = "SELECT * FROM track_label WHERE id_track=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id_track);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idTrack = resultSet.getInt("id_track");
                int idArtist = resultSet.getInt("id_artist");
                int idRecordLabel = resultSet.getInt("id_record_label");
                int year_edition = resultSet.getInt("year_edition");
                TrackLabel trackLabel = new TrackLabel(idRecordLabel, idTrack, idArtist,year_edition );
                trackLabels.add(trackLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackLabels;
    }

    @Override
    public List<TrackLabel> getTrackLabelsByArtistId(int id_artist) {
        List<TrackLabel> trackLabels = new ArrayList<>();
        String sql = "SELECT * FROM track_label WHERE id_artist=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id_artist);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idTrack = resultSet.getInt("id_track");
                int idArtist = resultSet.getInt("id_artist");
                int idRecordLabel = resultSet.getInt("id_record_label");
                int year_edition = resultSet.getInt("year_edition");
                TrackLabel trackLabel = new TrackLabel(idRecordLabel, idTrack, idArtist,year_edition );
                trackLabels.add(trackLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackLabels;
    }

    @Override
    public void updateTrackLabel(TrackLabel trackLabel) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE track_label SET year_edition = ? WHERE id_record_label = ? AND id_track = ? AND id_artist = ?");
            preparedStatement.setInt(1, trackLabel.getYear_edition());
            preparedStatement.setInt(2, trackLabel.getId_record_label());
            preparedStatement.setInt(3, trackLabel.getId_track());
            preparedStatement.setInt(4, trackLabel.getId_artist());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTrackLabelByRecordLabelId(int id_record_label) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM track_label WHERE id_record_label = ?");
            preparedStatement.setInt(1, id_record_label);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTrackLabelByTrackId(int id_track) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM track_label WHERE id_track = ?");
            preparedStatement.setInt(1, id_track);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTrackLabelByArtistId(int id_artist) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM track_label WHERE id_artist = ?");
            preparedStatement.setInt(1, id_artist);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
