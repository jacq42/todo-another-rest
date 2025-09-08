package de.jkrech.tutorial.todo.ports.db;

import de.jkrech.tutorial.todo.application.TodoRepository;
import de.jkrech.tutorial.todo.domain.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TodoJpaRepository.class)
@ActiveProfiles("test")
class TodoJpaRepositoryIntegrationTest {

    private static final String TITLE = "Fancy TODO title";

    @Autowired
    private TodoRepository testee;

    @AfterEach
    void tearDown() {
        testee.deleteAll();
    }

    @Test
    void shouldSaveNewTodo() {
        // given
        final Todo newTodo = Todo.createNewWith(TITLE);

        // when
        final Todo result = testee.save(newTodo);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldListAllTodos() {
        // given
        final Todo newTodo = Todo.createNewWith(TITLE);
        testee.save(newTodo);

        // when
        final List<Todo> allTodos = testee.findAll();

        // then
        assertThat(allTodos)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(newTodo);
    }

    @Test
    void shouldDeleteTodoById() {
        // given
        final Todo newTodo = Todo.createNewWith(TITLE);

        testee.save(newTodo);
        assertThat(testee.findById(newTodo.id())).isPresent();

        // when
        testee.deleteById(newTodo.id());

        // then
        assertThat(testee.findById(newTodo.id())).isEmpty();
    }

    @Test
    void shouldReturnTrueIfTodoExists() {
        // given
        final Todo newTodo = Todo.createNewWith(TITLE);
        testee.save(newTodo);

        // when
        boolean exists = testee.existsById(newTodo.id());

        // then
        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalseIfTodoDoesNotExist() {
        // when
        boolean exists = testee.existsById(UUID.randomUUID());

        // then
        assertThat(exists).isFalse();
    }

}