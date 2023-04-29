package com.example.tereschenko.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "record_label")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordLabel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_record_label;
    private String name;

    // getters and setters
}
