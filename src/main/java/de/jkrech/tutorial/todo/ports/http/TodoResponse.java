package de.jkrech.tutorial.todo.ports.http;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record TodoResponse(
        @NotBlank UUID id,
        @NotBlank String title
) {}