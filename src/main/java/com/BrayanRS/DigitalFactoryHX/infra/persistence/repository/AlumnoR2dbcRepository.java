package com.BrayanRS.DigitalFactoryHX.infra.persistence.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.entity.AlumnoEntity;

import reactor.core.publisher.Flux;

@Repository
public interface AlumnoR2dbcRepository extends R2dbcRepository<AlumnoEntity, Integer> {
    Flux<AlumnoEntity> findByEstado(Estado estado);
}
