package com.machowina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ParkingSpacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSpacesApplication.class, args);
	}
}
