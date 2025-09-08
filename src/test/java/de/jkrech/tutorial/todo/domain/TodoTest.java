package de.jkrech.tutorial.todo.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TodoTest {

    @Test
    void shouldCreateNewTodoWithTitle() {
        // given
        final String title = "My first todo";

        // when
        final var todo = Todo.createNewWith(title);

        // then
        assertThat(todo).isNotNull();
        assertThat(todo.id()).isNotNull();
        assertThat(todo.title().value()).isEqualTo("My first todo");
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "   ", "\t", "\n" })
    @NullAndEmptySource
    void shouldThrowExceptionWhenTitleIsInvalid(final String invalidTitle) {
        // when / then
        try {
            Todo.createNewWith(invalidTitle);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Title must not be null or blank");
        }
    }

}