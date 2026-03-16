package com.BrayanRS.DigitalFactoryHX.infra.persistence;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.model.Estado;
import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Adaptador de Persistencia en Memoria (Driven/Secondary Adapter).
 * 
 * Es parte de la capa de Infraestructura que implementa el Puerto de Salida
 * (Output Port)
 * para la persistencia de datos solicitada en el reto ("persistencia en memoria
 * usando
 * colección Java o BD en memoria").
 * 
 * Responsabilidad:
 * - Implementar los métodos de la interfaz `AlumnoRepositoryPort`
 * - Encapsular el detalle técnico de si se usa un `ConcurrentHashMap` o `List`
 * en memoria.
 * - Mapear Entities/DTOs internos del adaptador y traducirlos al dominio si
 * fuera estrictamente
 * necesario, o en este caso básico usar directamente la entidad de la capa de
 * modelo.
 */
@Component
@Profile("memoria")
public class AlumnoInMemoryAdapter implements AlumnoRepositoryPort {
    private final Map<Integer, Alumno> store = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> save(Alumno alumno) {
        return Mono.fromRunnable(() -> store.put(alumno.getId(), alumno));
    }

    @Override
    public Flux<Alumno> findActiveAlumnos() {
        return Flux.fromIterable(store.values())
                .filter(alumno -> alumno.getEstado() == Estado.ACTIVO);
    }

    @Override
    public Mono<Boolean> existsById(Integer id) {
        return Mono.just(store.containsKey(id));
    }
}
