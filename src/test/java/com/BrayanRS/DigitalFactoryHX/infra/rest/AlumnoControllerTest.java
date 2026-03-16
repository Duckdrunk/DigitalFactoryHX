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
}
