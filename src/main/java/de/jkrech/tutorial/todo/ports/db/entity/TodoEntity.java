package de.jkrech.tutorial.todo.ports.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "todos")
@Getter
@Setter
public class TodoEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column
    private String description;

    @Column(nullable = false)
    private boolean completed = false;

}