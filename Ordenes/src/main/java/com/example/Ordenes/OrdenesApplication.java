package com.example.Ordenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrdenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdenesApplication.class, args);
	}

	 // Aquí registramos el RestTemplate para que el Service lo pueda usar con @Autowired
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}