package com.multiplicatalent.talent.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Colores")
public class Color {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String color;
    private String pantone;
    private Integer year;
}
