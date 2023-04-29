package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "compositor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compositor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_compositor;
    private String name;

    // getters and setters
}

