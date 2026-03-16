package com.BrayanRS.DigitalFactoryHX.infra.persistence.entity;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("alumnos")
public class AlumnoEntity implements Persistable<Integer> {
    @Id
    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotNull
    private Estado estado;
    @Min(0)
    private Integer edad;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Transient
    private boolean isNew = true;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setAsNew() {
        this.isNew = true;
    }

    public void setAsNotNew() {
        this.isNew = false;
    }
}
