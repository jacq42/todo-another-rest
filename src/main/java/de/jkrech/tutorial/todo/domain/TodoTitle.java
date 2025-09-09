package de.jkrech.tutorial.todo.domain;

import lombok.Value;

@Value
public class TodoTitle {

    public static TodoTitle of(final String title) {
        return new TodoTitle(title);
    }

    String title;

    private TodoTitle(final String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
        this.title = title;
    }

    public String value() {
        return title;
    }
}
