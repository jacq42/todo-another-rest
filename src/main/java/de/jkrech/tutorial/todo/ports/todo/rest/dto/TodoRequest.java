package de.jkrech.tutorial.todo.ports.todo.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record TodoRequest(

        @Schema(name = "title", description = "Title", example = "Fancy task name")
        @JsonProperty(value = "title", required = true)
        @NotBlank String title
) {}
