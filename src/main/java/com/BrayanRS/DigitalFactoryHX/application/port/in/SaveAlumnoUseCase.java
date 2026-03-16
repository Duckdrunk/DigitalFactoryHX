package com.BrayanRS.DigitalFactoryHX.application.port.in;

/**
 * Puerto de Entrada (Input Port) para el Caso de Uso de Guardar Alumno.
 * 
 * En la Arquitectura Hexagonal, las interfaces en 'port.in' definen las
 * operaciones que la aplicación expone hacia el exterior (ej. Controladores
 * REST).
 * 
 * Responsabilidad:
 * - Recibir los datos de un alumno a ser guardado.
 * - Validar la consistencia de los campos del alumno.
 * - Asegurar que no se registre un alumno con un ID ya existente.
 * - Orquestar la invocación al puerto de salida (RepositoryPort) para la
 * persistencia.
 */
public interface SaveAlumnoUseCase {
    // Mono<Void> save(Alumno alumno);
}
