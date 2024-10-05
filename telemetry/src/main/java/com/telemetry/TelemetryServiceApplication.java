package com.telemetry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		servers = {
				@Server(url = "http://localhost:8000/telemetry", description = "Default Server URL")
		}
)

@SpringBootApplication
public class TelemetryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelemetryServiceApplication.class, args);
	}
}
