package de.jkrech.tutorial.todo.ports.db;

import de.jkrech.tutorial.todo.application.TodoRepository;
import de.jkrech.tutorial.todo.domain.Todo;
import de.jkrech.tutorial.todo.ports.db.entity.TodoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class TodoJpaRepository implements TodoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Todo save(Todo todo) {
        TodoEntity entity = toEntity(todo);
        if (entity.getId() == null) {
            entityManager.persist(entity);
        } else {
            entity = entityManager.merge(entity);
        }
        return toDomain(entity);
    }

    @Override
    public Optional<Todo> findById(UUID id) {
        final TodoEntity entity = entityManager.find(TodoEntity.class, id);
        return Optional.ofNullable(entity).map(this::toDomain);
    }

    @Override
    public List<Todo> findAll() {
        return entityManager.createQuery("SELECT t FROM TodoEntity t", TodoEntity.class)
                .getResultList()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        final TodoEntity entity = entityManager.find(TodoEntity.class, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM TodoEntity").executeUpdate();
    }

    @Override
    public boolean existsById(UUID id) {
        final Long count = entityManager.createQuery(
                        "SELECT COUNT(t) FROM TodoEntity t WHERE t.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    private TodoEntity toEntity(Todo todo) {
        TodoEntity entity = new TodoEntity();
        entity.setId(todo.id());
        entity.setTitle(todo.title().value());
        entity.setDescription("not ye implemented");
        entity.setCompleted(false);
        return entity;
    }

    private Todo toDomain(TodoEntity entity) {
        return Todo.of(entity.getId(), entity.getTitle());
    }
}