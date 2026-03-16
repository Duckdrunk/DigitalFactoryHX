package com.BrayanRS.DigitalFactoryHX.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    private Integer id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;
}
