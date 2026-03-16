package com.BrayanRS.DigitalFactoryHX.application.port.in;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import reactor.core.publisher.Flux;

/**
 * Puerto de Entrada (Input Port) para el Caso de Uso de Obtener Alumnos
 * Activos.
 * 
 * Responsabilidad:
 * - Definir la operación para consultar y retornar la lista de alumnos cuyo
 * estado sea 'ACTIVO'.
 * - Orquestar la invocación al puerto de salida (RepositoryPort) para la
 * recuperación de datos.
 */
public interface GetActiveAlumnosUseCase {
    Flux<Alumno> getActiveAlumnos();
}
