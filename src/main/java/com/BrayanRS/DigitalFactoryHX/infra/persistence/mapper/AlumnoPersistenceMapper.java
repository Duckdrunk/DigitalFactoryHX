package com.BrayanRS.DigitalFactoryHX.infra.persistence.mapper;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.entity.AlumnoEntity;

public class AlumnoPersistenceMapper {
    public static Alumno toDomain(AlumnoEntity entity) {
        if (entity == null)
            return null;
        return new Alumno(
                entity.getId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getEstado(),
                entity.getEdad());
    }

    public static AlumnoEntity toEntity(Alumno domain) {
        if (domain == null)
            return null;
        return AlumnoEntity.builder()
                .id(domain.getId())
                .nombre(domain.getNombre())
                .apellido(domain.getApellido())
                .estado(domain.getEstado())
                .edad(domain.getEdad())
                .isNew(true)
                .build();
    }
}
