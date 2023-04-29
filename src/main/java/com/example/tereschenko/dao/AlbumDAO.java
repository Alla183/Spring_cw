package com.example.tereschenko.dao;

import com.example.tereschenko.dto.AlbumTrackDTO;
import com.example.tereschenko.models.Album;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;
@Repository
public interface AlbumDAO {

    void createAlbum(Album album, Connection con);

    Album getAlbumById(int id, Connection con);

    List<Album> getAllAlbums(Connection con);

    List<AlbumTrackDTO> getAlbumTrack(int id_album, Connection con);

    void updateAlbum(Album album);

    void deleteAlbum(int id_album, Connection con);


}