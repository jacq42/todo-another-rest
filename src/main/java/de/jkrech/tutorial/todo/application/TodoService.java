package de.jkrech.tutorial.todo.application;

import de.jkrech.tutorial.todo.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo createAndAddTodoWithTitle(final String title) {
        final Todo newTodo = Todo.createNewWith(title);
        return todoRepository.save(newTodo);
    }

    public List<Todo> findAllTodos() {
        return todoRepository.findAll();
    }
}
