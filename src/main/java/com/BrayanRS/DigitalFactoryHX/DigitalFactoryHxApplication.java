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

	@Bean
	public CommandLineRunner debugRepository(AlumnoR2dbcRepository repository) {
		return args -> {
			System.out.println("--------------------------------------------");
			System.out.println("INVESTIGACIÓN DEL PROXY INVISIBLE:");

			// Ver el nombre de la clase generada en RAM
			System.out.println("Clase real: " + repository.getClass().getName());

			// Ver qué interfaces está "simulando" este objeto
			System.out.println("Interfaces implementadas: ");
			for (Class<?> iface : repository.getClass().getInterfaces()) {
				System.out.println(" - " + iface.getSimpleName());
			}
			System.out.println("--------------------------------------------");
		};
	}
}
