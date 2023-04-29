package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "track_playlist")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class TrackPlaylist {
    @Id
    private int id_track;
    private int id_playlist;
}
