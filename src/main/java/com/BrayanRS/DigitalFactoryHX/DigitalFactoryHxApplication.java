package com.BrayanRS.DigitalFactoryHX;

import com.BrayanRS.DigitalFactoryHX.domain.repository.AlumnoRepositoryPort;
import com.BrayanRS.DigitalFactoryHX.infra.persistence.repository.AlumnoR2dbcRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitalFactoryHxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalFactoryHxApplication.class, args);
	}
}
