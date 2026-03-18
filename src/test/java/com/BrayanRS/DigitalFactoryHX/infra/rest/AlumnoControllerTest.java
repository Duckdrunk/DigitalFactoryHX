package com.BrayanRS.DigitalFactoryHX.infra.rest;

import com.BrayanRS.DigitalFactoryHX.application.port.in.GetActiveAlumnosUseCase;
import com.BrayanRS.DigitalFactoryHX.application.port.in.SaveAlumnoUseCase;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.infra.rest.dto.AlumnoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(AlumnoController.class)
@Import(GlobalExceptionHandler.class)
public class AlumnoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private SaveAlumnoUseCase saveAlumnoUseCase;
    @MockitoBean
    private GetActiveAlumnosUseCase getActiveAlumnosUseCase;

    @Test
    @DisplayName("Debe retornar 400 Bad Request cuando los datos de entrada son inválidos")
    void save_WhenInvalidData_Returns400() {
        AlumnoRequest invalidRequest = new AlumnoRequest(1, "", "Ramirez", Estado.ACTIVO, 150);

        webTestClient.post()
                .uri("/api/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequest)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.errors.nombre").exists()
                .jsonPath("$.errors.edad").exists();
    }

    @Test
    @DisplayName("Debe retornar 201 Created cuando los datos son correctos")
    void save_WhenValidData_Returns201() {
        AlumnoRequest validRequest = new AlumnoRequest(1, "Brayan", "RS", Estado.ACTIVO, 25);

        when(saveAlumnoUseCase.save(any())).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/api/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(validRequest)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DisplayName("Debe retornar 409 Conflict cuando el Alumno ya existe")
    void save_WhenAlreadyExists_Returns409() {
        AlumnoRequest conflictRequest = new AlumnoRequest(2, "Luis", "Gomez", Estado.ACTIVO, 21);

        when(saveAlumnoUseCase.save(any()))
                .thenReturn(Mono.error(new com.BrayanRS.DigitalFactoryHX.domain.exception.AlumnoAlreadyExistsException(2)));

        webTestClient.post()
                .uri("/api/alumnos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(conflictRequest)
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody()
                .jsonPath("$.error").isEqualTo("Conflicto de Datos")
                .jsonPath("$.mensaje").isEqualTo("El Alumno con ID 2 ya existe en el sistema");
    }

    @Test
    @DisplayName("Debe retornar 200 y una lista de alumnos activos")
    void getActiveAlumnos_Returns200() {
        com.BrayanRS.DigitalFactoryHX.domain.model.Alumno alumno1 = new com.BrayanRS.DigitalFactoryHX.domain.model.Alumno(1, "Ana", "Ruiz", Estado.ACTIVO, 22);

        when(getActiveAlumnosUseCase.getActiveAlumnos())
                .thenReturn(reactor.core.publisher.Flux.just(alumno1));

        webTestClient.get()
                .uri("/api/alumnos")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].nombre").isEqualTo("Ana")
                .jsonPath("$[0].estado").isEqualTo("ACTIVO");
    }
}
