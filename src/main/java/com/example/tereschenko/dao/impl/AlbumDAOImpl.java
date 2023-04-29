package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.AlbumDAO;
import com.example.tereschenko.dto.AlbumTrackDTO;
import com.example.tereschenko.models.Album;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class AlbumDAOImpl implements AlbumDAO {

    private Connection connection;

    public AlbumDAOImpl() {
        this.connection = connection;
    }

    @Override
    public void createAlbum(Album album, Connection con) {
        String sql = "INSERT INTO album (id_album, name, year) VALUES (?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, album.getId_album());
            statement.setString(2, album.getName());
            statement.setInt(3, album.getYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Album getAlbumById(int id, Connection con) {
        String sql = "SELECT * FROM album WHERE id_album = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Album album = new Album();
                album.setId_album(resultSet.getInt("id_album"));
                album.setName(resultSet.getString("name"));
                album.setYear(resultSet.getInt("year"));
                return album;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Album> getAllAlbums(Connection con) {
        String sql = "SELECT * FROM album";
        List<Album> albums = new ArrayList<>();
        try {
            if (!con.isClosed()) {
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Album album = new Album();
                    album.setId_album(resultSet.getInt("id_album"));
                    album.setName(resultSet.getString("name"));
                    album.setYear(resultSet.getInt("year"));
                    albums.add(album);
                }
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return albums;
    }

    @Override
    public List<AlbumTrackDTO> getAlbumTrack(int id_album, Connection con) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(
                    "SELECT t.id_track, t.name, t.duration FROM album a JOIN track t ON t.id_album=a.id_album WHERE a.id_album=?;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStatement.setInt(1, id_album);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<AlbumTrackDTO> albumTrackDTOS = new ArrayList<>();
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id_track = rs.getInt("id_track");
                String trackName = rs.getString("name");
                String duration = rs.getString("duration");
                AlbumTrackDTO dto = new AlbumTrackDTO(id_track, trackName, duration);
                albumTrackDTOS.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return albumTrackDTOS;
    }

    @Override
    public void updateAlbum(Album album) {
        String sql = "UPDATE album SET name = ?, year = ? WHERE id_album = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, album.getName());
            statement.setInt(2, album.getYear());
            statement.setInt(3, album.getId_album());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAlbum(int id_album, Connection con) {
        String sql = "DELETE FROM album WHERE id_album = ?";
        String sql1 = "DELETE FROM track WHERE id_album = ?";
        String sql2 = "DELETE FROM track_label WHERE id_track IN (SELECT id_track FROM track WHERE id_album = ?)";
        try  {
            PreparedStatement statement = con.prepareStatement(sql2);
            statement.setInt(1, id_album);
            statement.executeUpdate();
            PreparedStatement statement1 = con.prepareStatement(sql1);
            statement1.setInt(1, id_album);
            statement1.executeUpdate();
            PreparedStatement statement2 = con.prepareStatement(sql);
            statement2.setInt(1, id_album);
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}