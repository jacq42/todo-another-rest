package de.jkrech.tutorial.todo.ports.todo.rest;

import de.jkrech.tutorial.todo.application.TodoService;
import de.jkrech.tutorial.todo.domain.Todo;
import de.jkrech.tutorial.todo.ports.todo.rest.dto.TodoRequest;
import de.jkrech.tutorial.todo.ports.todo.rest.dto.TodoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@MockitoSettings
class TodoResourceTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoResource todoResource;

    @Test
    void shouldListAllTodos() {
        // given
        final Todo todo1 = Todo.of(UUID.randomUUID(), "First Todo");
        final Todo todo2 = Todo.of(UUID.randomUUID(), "Second Todo");
        when(todoService.findAllTodos()).thenReturn(List.of(todo1, todo2));

        final TodoResponse expectedResponse1 = new TodoResponse(todo1.id(), todo1.title().value());
        final TodoResponse expectedResponse2 = new TodoResponse(todo2.id(), todo2.title().value());

        // when
        final ResponseEntity<List<TodoResponse>> response = todoResource.listAllTodos();

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyInAnyOrder(expectedResponse1, expectedResponse2);
    }

    @Test
    void shouldCreateNewTodo() {
        // given
        final TodoRequest todoRequest = new TodoRequest("New Todo");
        final Todo todo = Todo.createNewWith(todoRequest.title());
        when(todoService.createAndAddTodoWithTitle(anyString())).thenReturn(todo);

        final TodoResponse expectedResponse = new TodoResponse(todo.id(), todo.title().value());

        // when
        final ResponseEntity<TodoResponse> response = todoResource.create(todoRequest);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }
}