package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.RecordLabelDAO;
import com.example.tereschenko.models.RecordLabel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecordLabelDAOImpl implements RecordLabelDAO {

    private final Connection conn;

    public RecordLabelDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createRecordLabel(RecordLabel recordLabel) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO record_label (name) VALUES (?)");
        ps.setString(1, recordLabel.getName());
        ps.executeUpdate();
    }

    @Override
    public RecordLabel getRecordLabelById(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM record_label WHERE id_record_label = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            RecordLabel recordLabel = new RecordLabel();
            recordLabel.setId_record_label(rs.getInt("id_record_label"));
            recordLabel.setName(rs.getString("name"));
            return recordLabel;
        }
        return null;
    }

    @Override
    public List<RecordLabel> getAllRecordLabels() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM record_label");
        ResultSet rs = ps.executeQuery();
        List<RecordLabel> recordLabels = new ArrayList<>();
        while (rs.next()) {
            RecordLabel recordLabel = new RecordLabel();
            recordLabel.setId_record_label(rs.getInt("id_record_label"));
            recordLabel.setName(rs.getString("name"));
            recordLabels.add(recordLabel);
        }
        return recordLabels;
    }

    @Override
    public void updateRecordLabel(RecordLabel recordLabel) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE record_label SET name = ? WHERE id_record_label = ?");
        ps.setString(1, recordLabel.getName());
        ps.setInt(2, recordLabel.getId_record_label());
        ps.executeUpdate();
    }

    @Override
    public void deleteRecordLabel(RecordLabel recordLabel) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("DELETE FROM record_label WHERE id_record_label = ?");
        ps.setInt(1, recordLabel.getId_record_label());
        ps.executeUpdate();
    }
}
