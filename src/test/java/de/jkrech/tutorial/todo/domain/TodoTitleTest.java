package de.jkrech.tutorial.todo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TodoTitleTest {

    @Test
    void shouldCreateValidTitle() {
        // given
        final String validTitle = "My Todo Title";

        // when
        final TodoTitle todoTitle = TodoTitle.of(validTitle);

        // then
        assertThat(todoTitle.value()).isEqualTo("My Todo Title");
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "   ", "\t", "\n" })
    @NullAndEmptySource
    void shouldThrowExceptionWhenTitleIsInvalid(final String invalidTitle) {
        // when / then
        try {
            TodoTitle.of(invalidTitle);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Title must not be null or blank");
        }
    }
}