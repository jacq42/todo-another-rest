package de.jkrech.tutorial.todo.ports.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "todos")
@Getter
@Setter
public class TodoEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean completed = false;

    public TodoEntity() {}

    public TodoEntity(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }
}