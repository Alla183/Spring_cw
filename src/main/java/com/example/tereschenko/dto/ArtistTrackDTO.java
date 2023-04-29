package com.example.tereschenko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistTrackDTO {
    private String name;
    private int id_track;
    private String trackName;
    private String duration;
}
