package com.example.tereschenko.dao;

import com.example.tereschenko.models.PlaylistPurpose;

import java.util.List;

public interface PlaylistPurposeDAO {
    void createPlaylistPurpose(PlaylistPurpose playlistPurpose);
    PlaylistPurpose getPlaylistPurposeById(int playlistId, int purposeId);
    List<PlaylistPurpose> getAllPlaylistPurposes();
    void updatePlaylistPurpose(PlaylistPurpose playlistPurpose);
    void deletePlaylistPurpose(PlaylistPurpose playlistPurpose);
}

