package de.jkrech.tutorial.todo.ports.todo.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
        @NotBlank String title
) {}
