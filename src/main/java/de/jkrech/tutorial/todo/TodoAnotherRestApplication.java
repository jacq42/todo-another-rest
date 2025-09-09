package de.jkrech.tutorial.todo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title = "TODO API",
        description = "API for creating and managing todo items",
        version = "1.0.0"
    )
)
@SpringBootApplication
public class TodoAnotherRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoAnotherRestApplication.class, args);
	}

}
