package com.BrayanRS.DigitalFactoryHX.application;

import com.BrayanRS.DigitalFactoryHX.application.service.AlumnoService;
import com.BrayanRS.DigitalFactoryHX.domain.exception.AlumnoAlreadyExistsException;
import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AlumnoServiceTest {

    @Mock
    private AlumnoRepositoryPort repositoryPort;

    @InjectMocks
    private AlumnoService alumnoService;

    private Alumno alumnoActivo1;
    private Alumno alumnoActivo2;

    @BeforeEach
    void setUp() {
        alumnoActivo1 = new Alumno(1, "Juan", "Perez", Estado.ACTIVO, 20);
        alumnoActivo2 = new Alumno(2, "Maria", "Gomez", Estado.ACTIVO, 22);
    }

    @Test
    void getActiveAlumnos_ShouldReturnFluxOfActiveAlumnos() {
        when(repositoryPort.findActiveAlumnos()).thenReturn(Flux.just(alumnoActivo1, alumnoActivo2));

        Flux<Alumno> result = alumnoService.getActiveAlumnos();

        StepVerifier.create(result)
                .expectNext(alumnoActivo1)
                .expectNext(alumnoActivo2)
                .verifyComplete();

        verify(repositoryPort, times(1)).findActiveAlumnos();
    }

    @Test
    void save_WhenAlumnoDoesNotExist_ShouldSaveSuccessfully() {
        when(repositoryPort.existsById(anyInt())).thenReturn(Mono.just(false));
        when(repositoryPort.save(any(Alumno.class))).thenReturn(Mono.empty());

        Mono<Void> result = alumnoService.save(alumnoActivo1);

        StepVerifier.create(result)
                .verifyComplete();

        verify(repositoryPort, times(1)).existsById(1);
        verify(repositoryPort, times(1)).save(alumnoActivo1);
    }

    @Test
    void save_WhenAlumnoAlreadyExists_ShouldThrowException() {
        when(repositoryPort.existsById(anyInt())).thenReturn(Mono.just(true));

        Mono<Void> result = alumnoService.save(alumnoActivo1);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof AlumnoAlreadyExistsException &&
                        throwable.getMessage().equals("El Alumno con ID " + alumnoActivo1.getId() + " ya existe en el sistema"))
                .verify();

        verify(repositoryPort, times(1)).existsById(1);
        verify(repositoryPort, times(0)).save(any(Alumno.class));
    }
}
