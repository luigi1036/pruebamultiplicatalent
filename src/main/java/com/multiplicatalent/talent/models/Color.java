package com.multiplicatalent.talent.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "Colores")
public class Color {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private String color;
    private String pantone;
    private Integer year;
}
