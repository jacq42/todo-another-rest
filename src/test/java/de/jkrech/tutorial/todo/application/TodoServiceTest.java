package de.jkrech.tutorial.todo.application;

import de.jkrech.tutorial.todo.domain.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings
class TodoServiceTest {

    @Mock
    private Todo todoMock;

    @Mock
    private TodoRepository todoRepositoryMock;

    @InjectMocks
    private TodoService testee;

    @Test
    void shouldCreateAndAddTodoWithTitle() {
        // given
        final String title = "My first todo";
        when(todoRepositoryMock.save(any())).thenReturn(todoMock);

        // when
        final Todo todo = testee.createAndAddTodoWithTitle(title);

        // then
        assertThat(todo).isEqualTo(todoMock);
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