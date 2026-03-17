package com.BrayanRS.DigitalFactoryHX.application.service;

import com.BrayanRS.DigitalFactoryHX.domain.exception.AlumnoAlreadyExistsException;
import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumnoServiceTest {
    @Mock
    private AlumnoRepositoryPort repositoryPort;

    @InjectMocks
    private AlumnoService alumnoService;

    @Test
    @DisplayName("Debe lanzar AlumnoAlreadyExistsException cuando el ID ya existe")
    void save_WhenIdExists_ThrowsException() {
        Alumno alumno = new Alumno(1, "Brayan", "RS", Estado.ACTIVO, 25);
        when(repositoryPort.existsById(1)).thenReturn(Mono.just(true));

        Mono<Void> result = alumnoService.save(alumno);

        StepVerifier.create(result)
                .expectError(AlumnoAlreadyExistsException.class)
                .verify();

        verify(repositoryPort, never()).save(any());
    }

    @Test
    @DisplayName("Debe guardar exitosamente cuando el alumno es nuevo")
    void save_WhenNewAlumno_Completes() {
        Alumno alumno = new Alumno(2, "Juan", "Perez", Estado.ACTIVO, 30);
        when(repositoryPort.existsById(2)).thenReturn(Mono.just(false));
        when(repositoryPort.save(any())).thenReturn(Mono.empty());

        Mono<Void> result = alumnoService.save(alumno);

        StepVerifier.create(result)
                .verifyComplete();

        verify(repositoryPort, times(1)).save(alumno);
    }

    @Test
    @DisplayName("Debe propagar el error cuando el repositorio falla inesperadamente")
    void save_WhenRepositoryFails_ReturnsError() {
        Alumno alumno = new Alumno(3, "Error", "Test", Estado.ACTIVO, 20);
        when(repositoryPort.existsById(3)).thenReturn(Mono.just(false));
        when(repositoryPort.save(any())).thenReturn(Mono.error(new RuntimeException("DB Connection Timeout")));

        Mono<Void> result = alumnoService.save(alumno);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException &&
                        throwable.getMessage().equals("DB Connection Timeout"))
                .verify();
    }

    @Test
    @DisplayName("Debe asegurar que el objeto enviado al repositorio es el correcto")
    void save_VerifyExactData() {
        Alumno alumno = new Alumno(10, "Validar", "Datos", Estado.ACTIVO, 25);
        when(repositoryPort.existsById(10)).thenReturn(Mono.just(false));
        when(repositoryPort.save(any())).thenReturn(Mono.empty());

        alumnoService.save(alumno).block();

        verify(repositoryPort).save(argThat(a ->
                a.getId().equals(10) &&
                        a.getNombre().equals("Validar") &&
                        a.getEstado().equals(Estado.ACTIVO)
        ));
    }

    @Test
    @DisplayName("Debe retornar los alumnos que el repositorio devuelva como activos")
    void findActiveAlumnos_ShouldReturnFluxFromRepository() {
        Alumno activo = new Alumno(1, "Brayan", "RS", Estado.ACTIVO, 25);

        when(repositoryPort.findActiveAlumnos()).thenReturn(Flux.just(activo));

        StepVerifier.create(alumnoService.getActiveAlumnos())
                .expectNext(activo)
                .verifyComplete();

        verify(repositoryPort, times(1)).findActiveAlumnos();
    }
}
