package com.BrayanRS.DigitalFactoryHX.application.port.in;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import reactor.core.publisher.Mono;

public interface SaveAlumnoUseCase {
    Mono<Void> save(Alumno alumno);
}
