package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "purpose")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purpose {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_purpose;
    private String name;

    // getters and setters
}

