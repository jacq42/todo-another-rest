package de.jkrech.tutorial.todo.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class TodoServiceTest {

    private TodoService testee;

    @BeforeEach
    void setUp() {
        testee = new TodoService();
    }

    @Test
    void shouldCreateAndAddTodoWithTitle() {
        // given
        final String title = "My first todo";

        // when
        final var todoId = testee.createAndAddTodoWithTitle(title);

        // then
        assertThat(todoId).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(strings = { "", " ", "   ", "\t", "\n" })
    @NullAndEmptySource
    void shouldThrowExceptionWhenTitleIsInvalid(final String invalidTitle) {
         // when / then
        try {
            testee.createAndAddTodoWithTitle(invalidTitle);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage()).isEqualTo("Title must not be null or blank");
        }
    }

}