package com.BrayanRS.DigitalFactoryHX.domain.exception;

public class AlumnoAlreadyExistsException extends RuntimeException{
    public AlumnoAlreadyExistsException(Integer id){
        super("El Alumno con ID " + id + " ya existe en el sistema");
    }
}
