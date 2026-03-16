package com.BrayanRS.DigitalFactoryHX.domain.repository;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Puerto de Salida (Output Port) para el Repositorio de Alumnos.
 * 
 * Es parte de la Capa de Dominio (o Capa de Aplicación dependiendo del enfoque
 * exacto, pero
 * generalmente se considera el puerto de salida de los casos de uso hacia los
 * datos abstractos).
 * 
 * Responsabilidad:
 * - Definir los contratos para buscar o guardar alumnos sin revelar
 * detalles específicos de la base de datos o almacenamiento en memoria.
 * - Es empleado por los Use Cases / Application Services pero implementado
 * por los Adapters de la capa de Infraestructura.
 */
public interface AlumnoRepositoryPort {
     Mono<Void> save(Alumno alumno);

     Flux<Alumno> findActiveAlumnos();

     Mono<Boolean> existsById(Integer id);
}
