package com.example.tereschenko.services;

import com.example.tereschenko.dao.impl.AlbumDAOImpl;
import com.example.tereschenko.dto.AlbumTrackDTO;
import com.example.tereschenko.models.Album;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;
@Service
public class AlbumService {
    private AlbumDAOImpl albumDAOImpl;

    public AlbumService(AlbumDAOImpl albumDAOImpl) {
        this.albumDAOImpl = albumDAOImpl;
    }

    public List<Album> getAllAlbums(Connection con) {
        return albumDAOImpl.getAllAlbums(con);
    }

    public Album getAlbumById(int id, Connection con){
        return albumDAOImpl.getAlbumById(id, con);
    }

    public void createAlbum(Album album, Connection con){
        albumDAOImpl.createAlbum(album, con);
    }
    public void deleteAlbum(int id_album, Connection con){
        albumDAOImpl.deleteAlbum(id_album, con);
    }

    public  List<AlbumTrackDTO> getAlbumTrack(int id_album, Connection con){return albumDAOImpl.getAlbumTrack(id_album, con);}
}