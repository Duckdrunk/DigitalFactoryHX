package com.BrayanRS.DigitalFactoryHX.application.service;

import com.BrayanRS.DigitalFactoryHX.application.port.in.SaveAlumnoUseCase;
import com.BrayanRS.DigitalFactoryHX.application.port.in.GetActiveAlumnosUseCase;
import com.BrayanRS.DigitalFactoryHX.domain.exception.AlumnoAlreadyExistsException;
import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoService implements SaveAlumnoUseCase, GetActiveAlumnosUseCase {
    private final AlumnoRepositoryPort repositoryPort;

    public AlumnoService(AlumnoRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Flux<Alumno> getActiveAlumnos() {
        return repositoryPort.findActiveAlumnos();
    }

    @Override
    public Mono<Void> save(Alumno alumno) {
        return repositoryPort.existsById(alumno.getId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new AlumnoAlreadyExistsException(alumno.getId()));
                    }
                    return repositoryPort.save(alumno);
                });
    }
}
