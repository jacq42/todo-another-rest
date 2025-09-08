package de.jkrech.tutorial.todo.application;

import de.jkrech.tutorial.todo.domain.Todo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TodoService {

    public UUID createAndAddTodoWithTitle(final String title) {
        final Todo newTodo = Todo.createNewWith(title);
        return newTodo.id();
    }
}
