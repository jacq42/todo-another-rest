package de.jkrech.tutorial.todo.domain;

import lombok.Value;

@Value
public class TodoTitle {

    private final String title;

    public static TodoTitle of(String title) {
        return new TodoTitle(title);
    }

    private TodoTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
        this.title = title;
    }

    public String value() {
        return title;
    }
}
