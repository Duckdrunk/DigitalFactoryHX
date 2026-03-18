package com.BrayanRS.DigitalFactoryHX.infra.persistence;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.repository.AlumnoR2dbcRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import(AlumnoR2dbcAdapter.class)
@ActiveProfiles("docker")
@Testcontainers(disabledWithoutDocker = true)
@EnabledIf(expression = "#{environment.acceptsProfiles('docker')}", loadContext = true)
class AlumnoR2dbcAdapterTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.r2dbc.repositories.enabled", () -> true);
        registry.add("spring.r2dbc.url", () -> "r2dbc:postgresql://"
                + postgres.getHost() + ":" + postgres.getFirstMappedPort()
                + "/" + postgres.getDatabaseName());
        registry.add("spring.r2dbc.username", postgres::getUsername);
        registry.add("spring.r2dbc.password", postgres::getPassword);
        registry.add("spring.sql.init.mode", () -> "always");
        registry.add("spring.sql.init.schema-locations", () -> "classpath:schema.sql");
    }

    @Autowired
    private AlumnoR2dbcAdapter adapter;
    @Autowired
    private AlumnoR2dbcRepository repository;

    @BeforeEach
    void cleanDatabase() {
        repository.deleteAll().block();
    }

    @Test
    @DisplayName("Debe guardar un alumno mediante el adaptador R2DBC")
    void save_ShouldInteractWithDatabaseSuccessfully() {
        Alumno alumno = new Alumno(10, "Integration", "Test", Estado.ACTIVO, 25);

        StepVerifier.create(adapter.save(alumno))
                .verifyComplete();

        StepVerifier.create(adapter.existsById(10))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    @DisplayName("Debe recuperar los alumnos con estado ACTIVO")
    void findActiveAlumnos_ShouldReturnOnlyActive() {
        Alumno activo = new Alumno(20, "Activo", "Usuario", Estado.ACTIVO, 30);
        Alumno inactivo = new Alumno(21, "Inactivo", "Usuario", Estado.INACTIVO, 30);

        StepVerifier.create(adapter.save(activo)
                        .then(adapter.save(inactivo))
                        .thenMany(adapter.findActiveAlumnos()))
                .expectNextMatches(a -> a.getId().equals(20) && a.getEstado() == Estado.ACTIVO)
                .expectNextCount(0)
                .verifyComplete();
    }
}
