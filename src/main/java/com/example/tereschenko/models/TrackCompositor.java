package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "track_compositor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackCompositor {

    @Id
    private int id_track;
    private int id_compositor;

    // getters and setters
}
