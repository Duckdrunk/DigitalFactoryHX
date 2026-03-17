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
