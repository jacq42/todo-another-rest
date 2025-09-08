package de.jkrech.tutorial.todo.application;

import de.jkrech.tutorial.todo.domain.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoRepository {

    Todo save(Todo todo);
    Optional<Todo> findById(UUID id);
    List<Todo> findAll();
    void deleteById(UUID id);

    void deleteAll();

    boolean existsById(UUID id);
}
