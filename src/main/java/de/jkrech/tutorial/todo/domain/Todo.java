package de.jkrech.tutorial.todo.domain;

import java.util.UUID;

public class Todo {

    public static Todo createNewWith(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
        Todo todo = new Todo();
        todo.id = UUID.randomUUID();
        todo.title = TodoTitle.of(title);
        return todo;
    }

    private UUID id;
    private TodoTitle title;

    public UUID id() {
        return id;
    }

    public TodoTitle title() {
        return title;
    }
}
