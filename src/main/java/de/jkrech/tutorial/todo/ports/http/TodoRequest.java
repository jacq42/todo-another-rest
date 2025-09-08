package de.jkrech.tutorial.todo.ports.http;

import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
        @NotBlank String title
) {}
