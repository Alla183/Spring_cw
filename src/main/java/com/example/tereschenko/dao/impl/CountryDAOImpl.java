package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.CountryDAO;
import com.example.tereschenko.models.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAOImpl implements CountryDAO {

    private Connection connection;

    public CountryDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Country country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO country (id_country, name) VALUES (?, ?)");
        statement.setInt(1, country.getId_country());
        statement.setString(2, country.getName());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Country getById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM country WHERE id_country = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        Country country = null;
        if (rs.next()) {
            country = new Country();
            country.setId_country(rs.getInt("id_country"));
            country.setName(rs.getString("name"));
        }

        rs.close();
        statement.close();
        return country;
    }

    @Override
    public List<Country> getAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM country");

        List<Country> countries = new ArrayList<>();
        while (rs.next()) {
            Country country = new Country();
            country.setId_country(rs.getInt("id_country"));
            country.setName(rs.getString("name"));
            countries.add(country);
        }

        rs.close();
        statement.close();
        return countries;
    }

    @Override
    public void update(Country country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE country SET name = ? WHERE id_country = ?");
        statement.setString(1, country.getName());
        statement.setInt(2, country.getId_country());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void delete(Country country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM country WHERE id_country = ?");
        statement.setInt(1, country.getId_country());
        statement.executeUpdate();
        statement.close();
    }
}
