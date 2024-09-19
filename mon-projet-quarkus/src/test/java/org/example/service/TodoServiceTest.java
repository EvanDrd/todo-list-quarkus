package org.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.example.model.Todo;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;


@QuarkusTest
public class TodoServiceTest {

    @Inject
    TodoService todoService;

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo("Apprendre Quarkus", "Terminer le tutoriel Quarkus");
        Todo createdTodo = todoService.createTodo(todo);
        assertNotNull(createdTodo.id);
        assertEquals("Apprendre Quarkus", createdTodo.title);
    }

    @Test
    public void testUpdateTodoStatus() {
        Todo todo = new Todo("Faire du sport", "Courir 5 km");
        todoService.createTodo(todo);
        Todo updatedTodo = todoService.updateTodoStatus(todo.id, true);
        assertTrue(updatedTodo.completed);
    }

    @Test
    public void testListAllTodos() {
        List<Todo> todos = todoService.listAllTodos();
        assertNotNull(todos);
    }

}
