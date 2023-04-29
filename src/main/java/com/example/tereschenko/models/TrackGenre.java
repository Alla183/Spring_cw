package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "track_genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackGenre {

    @Id
    private int id_track;
    private int id_genre;

    // getters and setters
}

