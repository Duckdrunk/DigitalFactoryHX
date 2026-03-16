package com.BrayanRS.DigitalFactoryHX.infra.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table("alumnos")
@AllArgsConstructor
@Builder
public class AlumnoEntity implements Persistable<Integer> {
    @Id
    private Integer id;
    private String nombre;
    private String apellido;
    private Estado estado;
    private Integer edad;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @Transient
    @Builder.Default
    private boolean isNew = true;

    public AlumnoEntity(Integer id, String nombre, String apellido, Estado estado, Integer edad){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estado = estado;
        this.edad = edad;
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
