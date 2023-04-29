package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.PurposeDAO;
import com.example.tereschenko.models.Purpose;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurposeDAOImpl implements PurposeDAO {

    private final Connection conn;

    public PurposeDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createPurpose(Purpose purpose) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO purpose (id_purpose, name) VALUES (?, ?)");
            ps.setInt(1, purpose.getId_purpose());
            ps.setString(2, purpose.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Purpose getPurposeById(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM purpose WHERE id_purpose = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Purpose purpose = new Purpose();
                purpose.setId_purpose(rs.getInt("id_purpose"));
                purpose.setName(rs.getString("name"));
                return purpose;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Purpose> getAllPurposes() {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM purpose");
            ResultSet rs = ps.executeQuery();
            List<Purpose> purposes = new ArrayList<>();
            while (rs.next()) {
                Purpose purpose = new Purpose();
                purpose.setId_purpose(rs.getInt("id_purpose"));
                purpose.setName(rs.getString("name"));
                purposes.add(purpose);
            }
            return purposes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePurpose(Purpose purpose) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE purpose SET name = ? WHERE id_purpose = ?");
            ps.setString(1, purpose.getName());
            ps.setInt(2, purpose.getId_purpose());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePurpose(Purpose purpose) {
        try  {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM purpose WHERE id_purpose = ?");
            ps.setInt(1, purpose.getId_purpose());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
