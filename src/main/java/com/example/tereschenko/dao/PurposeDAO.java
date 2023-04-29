package com.example.tereschenko.dao;

import com.example.tereschenko.models.Purpose;

import java.util.List;

public interface PurposeDAO {
    void createPurpose(Purpose purpose);

    Purpose getPurposeById(int id);

    List<Purpose> getAllPurposes();

    void updatePurpose(Purpose purpose);

    void deletePurpose(Purpose purpose);
}