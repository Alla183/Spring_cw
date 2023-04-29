package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.ArtistDAO;
import com.example.tereschenko.dto.ArtistTrackDTO;
import com.example.tereschenko.models.Artist;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class ArtistDAOImpl implements ArtistDAO {

    @Override
    public void createArtist(Artist artist, Connection conn) {
        String sql = "INSERT INTO artist (id_artist, name, gender, id_country, id_role, id_group, year_edition) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, artist.getId_artist());
            statement.setString(2, artist.getName());
            statement.setString(3, artist.getGender());
            statement.setInt(4, artist.getId_country());
            statement.setInt(5, artist.getId_role());
            statement.setInt(6, artist.getId_group());
            statement.setInt(7, artist.getYear_edition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Artist getArtistById(int id, Connection connection) {
        String sql = "SELECT * FROM artist WHERE id_artist = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId_artist(resultSet.getInt("id_artist"));
                artist.setName(resultSet.getString("name"));
                artist.setGender(resultSet.getString("gender"));
                artist.setId_country(resultSet.getInt("id_country"));
                artist.setId_role(resultSet.getInt("id_solo_artist"));
                artist.setId_group(resultSet.getInt("id_group"));
                artist.setYear_edition(resultSet.getInt("year_edition"));
                return artist;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Artist> getAllArtists( Connection connection) {
        String sql = "SELECT * FROM artist";
        List<Artist> artists = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId_artist(resultSet.getInt("id_artist"));
                artist.setName(resultSet.getString("name"));
                artist.setGender(resultSet.getString("gender"));
                artist.setId_country(resultSet.getInt("id_country"));
                artist.setId_role(resultSet.getInt("id_role"));
                artist.setId_group(resultSet.getInt("id_group"));
                artist.setYear_edition(resultSet.getInt("year_edition"));
                artists.add(artist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artists;
    }

    @Override
    public List<ArtistTrackDTO> getArtistTrack(int id_artist, Connection con) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = con.prepareStatement(
                    "SELECT a.name, t.id_track, t.name, t.duration FROM artist a JOIN track_label tl ON tl.id_artist=a.id_artist JOIN track t ON t.id_track= tl.id_track WHERE a.id_artist=?;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            preparedStatement.setInt(1, id_artist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<ArtistTrackDTO> artistTrackDTOS = new ArrayList<>();
        try (ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                int id_track = rs.getInt("id_track");
                String trackName = rs.getString("t.name");
                String duration = rs.getString("duration");
                ArtistTrackDTO dto = new ArtistTrackDTO(name, id_track, trackName, duration);
                artistTrackDTOS.add(dto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return artistTrackDTOS;
    }

    @Override
   public void updateArtist(Artist artist, Connection connection) {
        String sql = "UPDATE artist SET name = ?, gender = ?, id_country = ?, id_role = ?, id_group = ?, year_edition = ? WHERE id_artist = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, artist.getName());
            statement.setString(2, artist.getGender());
            statement.setInt(3, artist.getId_country());
            statement.setInt(4, artist.getId_role());
            statement.setInt(5, artist.getId_group());
            statement.setInt(6, artist.getYear_edition());
            statement.setInt(7, artist.getId_artist());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArtist(Artist artist, Connection connection) {
        String sql = "DELETE FROM artist WHERE id_artist = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, artist.getId_artist());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
