package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.TrackDAO;
import com.example.tereschenko.models.Track;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class TrackDAOImpl implements TrackDAO {


    @Override
    public void createTrack(Track track, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO track (name, id_album, duration) VALUES (?, ?, ?)");
            ps.setString(1, track.getName());
            ps.setInt(2, track.getId_album());
            ps.setString(3, track.getDuration());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Track getTrackById(int id, Connection con) {
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM track WHERE id_track = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Track track = new Track();
                    track.setId_track(rs.getInt("id_track"));
                    track.setName(rs.getString("name"));
                    track.setId_album(rs.getInt("id_album"));
                    track.setDuration(rs.getString("duration"));
                    return track;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Track> getAllTracks(Connection conn) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track");
            ResultSet rs = ps.executeQuery();
            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {
                Track track = new Track();
                track.setId_track(rs.getInt("id_track"));
                track.setName(rs.getString("name"));
                track.setId_album(rs.getInt("id_album"));
                track.setDuration(rs.getString("duration"));
                tracks.add(track);
            }
            return tracks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTrack(Track track, Connection con) {
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE track SET name = ?, id_album = ?, duration = ? WHERE id_track = ?");
            ps.setString(1, track.getName());
            ps.setInt(2, track.getId_album());
            ps.setString(3, track.getDuration());
            ps.setInt(4, track.getId_track());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTrack(int id, Connection con) {
        try  {
            PreparedStatement ps = con.prepareStatement("DELETE FROM track WHERE id_track = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
