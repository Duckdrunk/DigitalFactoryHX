package com.BrayanRS.DigitalFactoryHX.infra.persistence;

import com.BrayanRS.DigitalFactoryHX.domain.model.Alumno;
import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.entity.AlumnoEntity;

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
public class AlumnoInMemoryAdapter implements AlumnoRepositoryPort {
    // private final Map<Long, Alumno> store = new ConcurrentHashMap<>();
}
