package de.jkrech.tutorial.todo.domain;

import lombok.Value;

import java.util.UUID;

@Value
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
        return new Todo(id, TodoTitle.of(title));
    }

    UUID id;
    TodoTitle title;

    private Todo(final UUID id, final TodoTitle title) {
        this.id = id;
        this.title = title;
    }

    public UUID id() {
        return id;
    }

    public TodoTitle title() {
        return title;
    }
}
