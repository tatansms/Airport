package edu.unimagdalena.aereopuerto;

import org.springframework.boot.SpringApplication;

public class TestAereopuertoApplication {

	public static void main(String[] args) {
		SpringApplication.from(AereopuertoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
