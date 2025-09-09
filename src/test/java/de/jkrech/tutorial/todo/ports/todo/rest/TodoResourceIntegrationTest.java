package de.jkrech.tutorial.todo.ports.todo.rest;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.restassured.OpenApiValidationFilter;
import de.jkrech.tutorial.todo.IntegrationTest;
import de.jkrech.tutorial.todo.application.TodoRepository;
import de.jkrech.tutorial.todo.domain.Todo;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@AutoConfigureMockMvc
class TodoResourceIntegrationTest extends IntegrationTest {

    private static final String RESOURCE_BASE_PATH = "/todos";

    private OpenApiValidationFilter openApiValidationFilter;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    protected void setup() {
        super.setup();
        var validator = OpenApiInteractionValidator
                .createFor("/api-specs/todo-spec.yaml")
                .build();
        openApiValidationFilter = new OpenApiValidationFilter(validator);
        cleanup();
    }

    @AfterEach
    public void cleanup() {
        todoRepository.deleteAll();
    }

    @Test
    void shouldListAllTodos() throws Exception {
        // given
        createAndSaveSampleTodo(
                UUID.fromString("f413a86f-10cf-4d62-a6db-fa403323c07f"),
                "I really need to it first"
        );
        createAndSaveSampleTodo(
                UUID.fromString("a583f3a1-3164-434b-97c3-8eecda6bde43"),
                "Not so important task"
        );

        // when / then
        RestAssured.given()
                .filter(openApiValidationFilter)
                .when()
                .get(RESOURCE_BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("[0].id", equalTo("f413a86f-10cf-4d62-a6db-fa403323c07f"))
                .body("[0].title", equalTo("I really need to it first"))
                .body("[1].id", equalTo("a583f3a1-3164-434b-97c3-8eecda6bde43"))
                .body("[1].title", equalTo("Not so important task"));
    }

    @Test
    void shouldCreateNewTodo() throws Exception {
        // given
        final String requestJson = "{\"title\":\"Newly created task\"}";

        // when / then
        RestAssured.given()
                .filter(openApiValidationFilter)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(requestJson)
                .when()
                .post(RESOURCE_BASE_PATH)
                .then()
                .statusCode(201)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body("id", notNullValue())
                .body("title", equalTo("Newly created task"));
    }

    @Test
    void shouldReturnBadRequest() throws Exception {
        // given
        final String invalidRequestJson = "{\"unknowProperty\":\"Newly created task\"}";

        // when / then
        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(invalidRequestJson)
                .when()
                .post(RESOURCE_BASE_PATH)
                .then()
                .statusCode(400);
    }

    private void createAndSaveSampleTodo(final UUID uuid, final String title) {
        final Todo todo = Todo.of(uuid, title);
        todoRepository.save(todo);
    }
}