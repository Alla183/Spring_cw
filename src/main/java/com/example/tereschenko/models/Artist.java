package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artist")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_artist;
    private String name;
    private String gender;
    private int id_country;
    private int id_role;
    @Column(nullable = true, columnDefinition = "int default 0")
    private int id_group;
    private int year_edition;
}

