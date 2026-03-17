package com.BrayanRS.DigitalFactoryHX.domain.repository;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoRepositoryPort {
     Mono<Void> save(Alumno alumno);

     Flux<Alumno> findActiveAlumnos();

     Mono<Boolean> existsById(Integer id);
}
