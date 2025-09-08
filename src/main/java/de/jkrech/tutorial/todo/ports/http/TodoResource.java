package de.jkrech.tutorial.todo.ports.http;

import de.jkrech.tutorial.todo.application.TodoService;
import de.jkrech.tutorial.todo.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoResource {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<TodoResponse> listAllTodos() {
        return todoService.findAllTodos()
                .stream()
                .map(todo -> new TodoResponse(todo.id(), todo.title().value()))
                .toList();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public TodoResponse create(
            @RequestBody @Validated final TodoRequest request
    ) {
        final Todo todo = todoService.createAndAddTodoWithTitle(request.title());
        return new TodoResponse(todo.id(), todo.title().value());
    }
}
