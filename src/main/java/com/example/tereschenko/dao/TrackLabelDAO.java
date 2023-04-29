package com.example.tereschenko.dao;

import com.example.tereschenko.models.TrackLabel;

import java.util.List;

public interface TrackLabelDAO {
    void createTrackLabel(TrackLabel trackLabel);
    List<TrackLabel> getAllTrackLabels();
    List<TrackLabel> getTrackLabelsByRecordLabelId(int id_record_label);
    List<TrackLabel> getTrackLabelsByTrackId(int id_track);
    List<TrackLabel> getTrackLabelsByArtistId(int id_artist);
    void updateTrackLabel(TrackLabel trackLabel);
    void deleteTrackLabelByRecordLabelId(int id_record_label);
    void deleteTrackLabelByTrackId(int id_track);
    void deleteTrackLabelByArtistId(int id_artist);
}
