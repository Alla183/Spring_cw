package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "track_label")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class TrackLabel {
    @Id
    private int id_record_label;
    private int id_track;
    private int id_artist;
    private int year_edition;
}
