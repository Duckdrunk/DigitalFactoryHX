package com.BrayanRS.DigitalFactoryHX.infra.persistence.mapper;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.entity.AlumnoEntity;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.mapper.AlumnoPersistenceMapper;
import com.BrayanRS.DigitalFactoryHX.infra.rest.dto.AlumnoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AlumnoPersistenceMapperTest {

    @Test
    void requestToDomain_ShouldMapAllFieldsCorrectly() {
        AlumnoRequest request = new AlumnoRequest(1, "Brayan", "RS", Estado.ACTIVO, 25);

        Alumno domain = request.toDomain();

        assertEquals(request.id(), domain.getId());
        assertEquals(request.nombre(), domain.getNombre());
        assertEquals(request.apellido(), domain.getApellido());
        assertEquals(request.estado(), domain.getEstado());
        assertEquals(request.edad(), domain.getEdad());
    }

    @Test
    void entityToDomain_ShouldMapAllFieldsCorrectly(){
        AlumnoEntity entity = AlumnoEntity.builder()
                                .id(1)
                                .nombre("Brayan")
                                .apellido("RS")
                                .estado(Estado.ACTIVO)
                                .edad(25)
                                .build();

        Alumno domain = AlumnoPersistenceMapper.toDomain(entity);

        assertAll("Propiedades del Dominio",
                () -> assertEquals(entity.getId(), domain.getId(), "El ID debe coincidir"),
                () -> assertEquals(entity.getNombre(), domain.getNombre(), "El nombre debe coincidir"),
                () -> assertEquals(entity.getApellido(), domain.getApellido(), "El apellido debe coincidir"),
                () -> assertEquals(entity.getEstado(), domain.getEstado(), "El estado debe coincidir"),
                () -> assertEquals(entity.getEdad(), domain.getEdad(), "La edad debe coincidir")
        );
    }

    @Test
    void domainToEntity_ShouldMapAllFieldsCorrectly(){
        Alumno domain = new Alumno(1, "Brayan", "RS", Estado.ACTIVO, 25);

        AlumnoEntity entity = AlumnoPersistenceMapper.toEntityForCreate(domain);

        assertAll("Propiedades del Dominio",
                () -> assertEquals(entity.getId(), domain.getId(), "El ID debe coincidir"),
                () -> assertEquals(entity.getNombre(), domain.getNombre(), "El nombre debe coincidir"),
                () -> assertEquals(entity.getApellido(), domain.getApellido(), "El apellido debe coincidir"),
                () -> assertEquals(entity.getEstado(), domain.getEstado(), "El estado debe coincidir"),
                () -> assertEquals(entity.getEdad(), domain.getEdad(), "La edad debe coincidir"),
                () -> assertTrue(entity.isNew(), "La entidad debe marcarse como nueva para R2DBC"),
                () -> assertNotNull(entity.getCreatedDate(), "La fecha de creación debe generarse")
        );
    }

    @Test
    @DisplayName("Debe manejar un objeto null devolviendo null")
    void toDomain_WhenEntityIsNull_ReturnsNull() {
        Alumno domain = AlumnoPersistenceMapper.toDomain(null);
        assertNull(domain);
    }

    @Test
    @DisplayName("Debe manejar campos opcionales nulos")
    void toDomain_WhenOptionalFieldsAreNull_ShouldNotExplode() {
        //new AlumnoEntity(1, "Brayan", "RS", Estado.ACTIVO, null)
        AlumnoEntity entity = AlumnoEntity.builder()
                                .id(1)
                                .nombre("Brayan")
                                .apellido("RS")
                                .estado(Estado.ACTIVO)
                                .edad(null)
                                .createdDate(LocalDateTime.now())
                                .isNew(true)
                                .build();

        Alumno domain = AlumnoPersistenceMapper.toDomain(entity);

        assertNotNull(domain);
        assertNull(domain.getEdad());
    }
}
