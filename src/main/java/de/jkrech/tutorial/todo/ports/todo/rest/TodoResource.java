package de.jkrech.tutorial.todo.ports.todo.rest;

import de.jkrech.tutorial.todo.application.TodoService;
import de.jkrech.tutorial.todo.domain.Todo;
import de.jkrech.tutorial.todo.ports.todo.rest.dto.TodoRequest;
import de.jkrech.tutorial.todo.ports.todo.rest.dto.TodoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoResource {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoResponse>> listAllTodos() {
        final List<TodoResponse> todoResponseList = todoService.findAllTodos()
                .stream()
                .map(todo -> new TodoResponse(todo.id(), todo.title().value()))
                .toList();
        return ResponseEntity.ok(todoResponseList);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<TodoResponse> create(
            @RequestBody @Validated final TodoRequest request
    ) {
        final Todo todo = todoService.createAndAddTodoWithTitle(request.title());
        final TodoResponse todoResponse = new TodoResponse(todo.id(), todo.title().value());
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponse);
    }
}
