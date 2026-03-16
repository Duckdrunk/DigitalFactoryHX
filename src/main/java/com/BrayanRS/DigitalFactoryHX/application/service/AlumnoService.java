package com.BrayanRS.DigitalFactoryHX.application.service;

import com.BrayanRS.DigitalFactoryHX.application.port.in.SaveAlumnoUseCase;
import com.BrayanRS.DigitalFactoryHX.application.port.in.GetActiveAlumnosUseCase;

/**
 * Servicio de Aplicación (Application Service).
 * 
 * Implementa los puertos de entrada (Use Cases). Es el núcleo de la lógica de
 * negocio
 * y orquestación dentro del hexágono.
 * 
 * Responsabilidad:
 * - Implementar `SaveAlumnoUseCase` y `GetActiveAlumnosUseCase`.
 * - Hacer uso de los puertos de salida (ej. `AlumnoRepositoryPort`) inyectados
 * por inversión de control para guardar o consultar los alumnos.
 * - Lanzar errores o excepciones de negocio (ej. "Id repetido").
 */
public class AlumnoService implements SaveAlumnoUseCase, GetActiveAlumnosUseCase {
    // private final AlumnoRepositoryPort repositoryPort;
}
