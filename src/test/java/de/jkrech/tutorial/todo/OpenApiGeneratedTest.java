package de.jkrech.tutorial.todo;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class OpenApiGeneratedIntegrationTest {

    @Test
    void shouldGenerateOpenApiDocumentation() {
        // given
        final File openApiFile = new File("target/generatedOpenApiSpec.yaml");

        // when / then
        assertThat(openApiFile)
                .withFailMessage("OpenApi specification file not found.")
                .exists();
    }
}