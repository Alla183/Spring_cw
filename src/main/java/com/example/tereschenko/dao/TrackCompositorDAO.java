package com.example.tereschenko.dao;

import com.example.tereschenko.models.TrackCompositor;

import java.util.List;

public interface TrackCompositorDAO {
    void createTrackCompositor(TrackCompositor trackCompositor);
    List<TrackCompositor> getAllTrackCompositors();
    List<TrackCompositor> getTrackCompositorsByTrackId(int id_track);
    List<TrackCompositor> getTrackCompositorsByCompositorId(int id_compositor);
    void deleteTrackCompositorByTrackId(int id_track);
    void deleteTrackCompositorByCompositorId(int id_compositor);
}
