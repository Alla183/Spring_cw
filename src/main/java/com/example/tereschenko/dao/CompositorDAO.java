package com.example.tereschenko.dao;

import com.example.tereschenko.models.Compositor;

import java.util.List;

public interface CompositorDAO {
    void createCompositor(Compositor compositor);
    Compositor getCompositorById(int id);
    List<Compositor> getAllCompositors();
    void updateCompositor(Compositor compositor);
    void deleteCompositor(Compositor compositor);
}
