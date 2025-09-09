package de.jkrech.tutorial.todo.ports.todo.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TodoResponse(

        @Schema(name = "id", description = "Unique identifier", example = "f413a86f-10cf-4d62-a6db-fa403323c07f")
        @JsonProperty(value = "id", required = true)
        @NotBlank UUID id,

        @Schema(name = "title", description = "Title", example = "Fancy task name")
        @JsonProperty(value = "title", required = true)
        @NotBlank String title
) {}