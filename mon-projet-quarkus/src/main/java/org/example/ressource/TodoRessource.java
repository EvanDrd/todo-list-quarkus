package org.example.ressource;

import java.util.List;

import org.example.model.Todo;
import org.example.service.TodoService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoRessource {

    @Inject
    TodoService todoService;

    @GET
    public List<Todo> getAllTodos() {
        return todoService.listAllTodos();
    }

    @POST
    public Response createTodo(Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return Response.status(Response.Status.CREATED).entity(createdTodo).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateTodoStatus(@PathParam("id") Long id, Todo todo) {
        Todo existingTodo = Todo.findById(id);
        if (existingTodo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Mise à jour de la propriété `completed`
        existingTodo.completed = todo.completed;
        
        // Persistance n'est pas nécessaire car Panache gère la mise à jour automatiquement
        return Response.ok(existingTodo).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteTodo(@PathParam("id") Long id) {
        todoService.deleteTodoById(id);
        return Response.noContent().build();
    }

}
