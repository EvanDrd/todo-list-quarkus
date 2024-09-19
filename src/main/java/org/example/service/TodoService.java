package org.example.service;

import java.util.List;

import org.example.model.Todo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TodoService {

    public List<Todo> listAllTodos() {
        return Todo.listAll();
    }

    @Transactional
    public Todo createTodo(Todo todo) {
        todo.persist();
        return todo;
    }

    public Todo updateTodoStatus(Long id, boolean completed) {
        Todo todo = Todo.findById(id);
        if (todo != null) {
            todo.completed = completed;
            todo.persist();
        }
        return todo;
    }

    public void deleteTodoById(Long id) {
        Todo todo = Todo.findById(id);
        if (todo != null) {
            todo.delete();
        }
    }

}
