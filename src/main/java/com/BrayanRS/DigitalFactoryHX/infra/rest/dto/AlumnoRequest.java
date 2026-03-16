package com.BrayanRS.DigitalFactoryHX.infra.rest.dto;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlumnoRequest(
    @NotNull(message = "El ID es requerido")
    Integer id,

    @NotBlank(message = "El nombre es requerido")
    String nombre,

    @NotBlank(message = "El apellido es requerido")
    String apellido,

    @NotNull(message = "El estado debe ser ACTIVO o INACTIVO")
    Estado estado,

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad debe ser un valor válido")
    Integer edad
) {
    public Alumno toDomain() {
        return new Alumno(this.id, this.nombre, this.apellido, this.estado, this.edad);
    }
}
