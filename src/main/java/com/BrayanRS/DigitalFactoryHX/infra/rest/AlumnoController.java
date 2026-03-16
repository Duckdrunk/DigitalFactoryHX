package com.BrayanRS.DigitalFactoryHX.infra.rest;

import com.BrayanRS.DigitalFactoryHX.application.port.in.SaveAlumnoUseCase;
import com.BrayanRS.DigitalFactoryHX.application.port.in.GetActiveAlumnosUseCase;

/**
 * REST Controller (Driving/Primary Adapter).
 * 
 * Es parte de la capa de Infraestructura que actúa como puerto de interfaz de
 * usuario/API.
 * 
 * Responsabilidad:
 * - Exponer los endpoints REST (ej. POST /alumnos, GET /alumnos/activos).
 * - Mapear los DTOs entrantes (requests) al modelo de la capa de Dominio o
 * Commands de Aplicación.
 * - Invocar los Casos de Uso (Ports In) para ejecutar el pedido
 * ('SaveAlumnoUseCase' y 'GetActiveAlumnosUseCase').
 * - Formatear la respuesta (responses) según requerimiento y enviar códigos
 * HTTP de estado correctos o lanzar excepciones reactivas.
 */
public class AlumnoController {
    // private final SaveAlumnoUseCase saveAlumnoUseCase;
    // private final GetActiveAlumnosUseCase getActiveAlumnosUseCase;
}
