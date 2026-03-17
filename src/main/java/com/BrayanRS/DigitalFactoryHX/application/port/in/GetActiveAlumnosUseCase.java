package com.BrayanRS.DigitalFactoryHX.application.port.in;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import reactor.core.publisher.Flux;

public interface GetActiveAlumnosUseCase {
    Flux<Alumno> getActiveAlumnos();
}
