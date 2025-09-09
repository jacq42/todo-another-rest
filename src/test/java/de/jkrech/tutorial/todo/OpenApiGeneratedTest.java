package de.jkrech.tutorial.todo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiGeneratedTest extends IntegrationTest {

    @Test
    @Disabled
    void shouldGenerateOpenApiDocumentation() {
        // given
        final String openApiSpec = readResource("META-INF/swagger/todo-1.0.0.yml");

        // when /
        assertThat(openApiSpec).isNotEmpty();
    }
}