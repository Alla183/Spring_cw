package com.example.tereschenko.dao.impl;

import com.example.tereschenko.dao.CompositorDAO;
import com.example.tereschenko.models.Compositor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompositorDAOImpl implements CompositorDAO {

    private final Connection connection;

    public CompositorDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createCompositor(Compositor compositor) {
        String sql = "INSERT INTO compositor (id_compositor, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compositor.getId_compositor());
            statement.setString(2, compositor.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Compositor getCompositorById(int id) {
        String sql = "SELECT * FROM compositor WHERE id_compositor = ?";
        Compositor compositor = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                compositor = new Compositor();
                compositor.setId_compositor(resultSet.getInt("id_compositor"));
                compositor.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compositor;
    }

    @Override
    public List<Compositor> getAllCompositors() {
        String sql = "SELECT * FROM compositor";
        List<Compositor> allCompositors = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Compositor compositor = new Compositor();
                compositor.setId_compositor(resultSet.getInt("id_compositor"));
                compositor.setName(resultSet.getString("name"));
                allCompositors.add(compositor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCompositors;
    }

    @Override
    public void updateCompositor(Compositor compositor) {
        String sql = "UPDATE compositor SET name = ? WHERE id_compositor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, compositor.getName());
            statement.setInt(2, compositor.getId_compositor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompositor(Compositor compositor) {
        String sql = "DELETE FROM compositor WHERE id_compositor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, compositor.getId_compositor());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
