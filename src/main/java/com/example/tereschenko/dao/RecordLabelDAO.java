package com.example.tereschenko.dao;

import com.example.tereschenko.models.RecordLabel;

import java.sql.SQLException;
import java.util.List;

public interface RecordLabelDAO {
    void createRecordLabel(RecordLabel recordLabel) throws SQLException;

    RecordLabel getRecordLabelById(int id) throws SQLException;

    List<RecordLabel> getAllRecordLabels() throws SQLException;

    void updateRecordLabel(RecordLabel recordLabel) throws SQLException;

    void deleteRecordLabel(RecordLabel recordLabel) throws SQLException;
}
