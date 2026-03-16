package com.BrayanRS.DigitalFactoryHX.infra.persistence;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.entity.AlumnoEntity;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.mapper.AlumnoPersistenceMapper;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.repository.AlumnoR2dbcRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("db")
public class AlumnoR2dbcAdapter implements AlumnoRepositoryPort {

    private final AlumnoR2dbcRepository repository;

    public AlumnoR2dbcAdapter(AlumnoR2dbcRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> save(Alumno alumno) {
        AlumnoEntity entity = AlumnoPersistenceMapper
                .toEntity(alumno);
        return repository.save(entity).then();
    }

    @Override
    public Flux<Alumno> findActiveAlumnos() {
        return repository.findByEstado(Estado.ACTIVO)
                .map(AlumnoPersistenceMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return repository.existsById(id);
    }

}
