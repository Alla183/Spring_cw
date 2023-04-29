package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.TrackCompositorDAO;
import com.example.tereschenko.models.TrackCompositor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackCompositorDAOImpl implements TrackCompositorDAO {

    private Connection conn;

    public TrackCompositorDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createTrackCompositor(TrackCompositor trackCompositor) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO track_compositor (id_track, id_compositor) VALUES (?, ?)");
            ps.setInt(1, trackCompositor.getId_track());
            ps.setInt(2, trackCompositor.getId_compositor());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TrackCompositor> getAllTrackCompositors() {
        List<TrackCompositor> trackCompositors = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_compositor");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackCompositor trackCompositor = new TrackCompositor();
                trackCompositor.setId_track(rs.getInt("id_track"));
                trackCompositor.setId_compositor(rs.getInt("id_compositor"));
                trackCompositors.add(trackCompositor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackCompositors;
    }

    @Override
    public List<TrackCompositor> getTrackCompositorsByTrackId(int id_track) {
        List<TrackCompositor> trackCompositors = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_compositor WHERE id_track = ?");
            ps.setInt(1, id_track);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackCompositor trackCompositor = new TrackCompositor();
                trackCompositor.setId_track(rs.getInt("id_track"));
                trackCompositor.setId_compositor(rs.getInt("id_compositor"));
                trackCompositors.add(trackCompositor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackCompositors;
    }

    @Override
    public List<TrackCompositor> getTrackCompositorsByCompositorId(int id_compositor) {
        List<TrackCompositor> trackCompositors = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM track_compositor WHERE id_compositor = ?");
            ps.setInt(1, id_compositor);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TrackCompositor trackCompositor = new TrackCompositor();
                trackCompositor.setId_track(rs.getInt("id_track"));
                trackCompositor.setId_compositor(rs.getInt("id_compositor"));
                trackCompositors.add(trackCompositor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trackCompositors;
    }

    @Override
    public void deleteTrackCompositorByTrackId(int id_track) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM track_compositor WHERE id_track = ?");
            ps.setInt(1, id_track);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteTrackCompositorByCompositorId(int id_compositor) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM track_compositor WHERE id_compositor = ?");
            ps.setInt(1, id_compositor);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
