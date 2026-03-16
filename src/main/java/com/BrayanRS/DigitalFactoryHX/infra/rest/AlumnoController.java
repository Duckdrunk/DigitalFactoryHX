package com.BrayanRS.DigitalFactoryHX.infra.rest;

import com.BrayanRS.DigitalFactoryHX.application.port.in.SaveAlumnoUseCase;
import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;

import com.BrayanRS.DigitalFactoryHX.infra.rest.dto.AlumnoRequest;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;

import com.BrayanRS.DigitalFactoryHX.application.port.in.GetActiveAlumnosUseCase;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {
    private final SaveAlumnoUseCase saveAlumnoUseCase;
    private final GetActiveAlumnosUseCase getActiveAlumnosUseCase;

    public AlumnoController(SaveAlumnoUseCase saveAlumnoUseCase, GetActiveAlumnosUseCase getActiveAlumnosUseCase) {
        this.saveAlumnoUseCase = saveAlumnoUseCase;
        this.getActiveAlumnosUseCase = getActiveAlumnosUseCase;
    }

    @PostMapping
    public Mono<Void> save(@Valid @RequestBody AlumnoRequest request) {
        return saveAlumnoUseCase.save(request.toDomain());
    }

    @GetMapping
    public Flux<Alumno> getActiveAlumnos() {
        return getActiveAlumnosUseCase.getActiveAlumnos();
    }
}
