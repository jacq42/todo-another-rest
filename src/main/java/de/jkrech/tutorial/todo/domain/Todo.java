package de.jkrech.tutorial.todo.domain;

import lombok.ToString;

import java.util.UUID;

@ToString
public class Todo {

    public static Todo createNewWith(final String title) {
        return of(UUID.randomUUID(), title);
    }

    public static Todo of(final UUID id, final String title) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
        Todo todo = new Todo();
        todo.id = id;
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
