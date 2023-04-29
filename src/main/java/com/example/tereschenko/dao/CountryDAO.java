package com.example.tereschenko.dao;

import com.example.tereschenko.models.Country;
import java.sql.SQLException;
import java.util.List;

public interface CountryDAO {
    public void save(Country country) throws SQLException;
    public Country getById(int id) throws SQLException;
    public List<Country> getAll() throws SQLException;
    public void update(Country country) throws SQLException;
    public void delete(Country country) throws SQLException;
}
