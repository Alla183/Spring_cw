package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlist_purpose")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistPurpose {

    @Id

    private int id_playlist;
    private int id_purpose;

    // getters and setters
}
