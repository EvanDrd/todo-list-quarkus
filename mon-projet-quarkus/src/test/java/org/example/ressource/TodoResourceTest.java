package org.example.ressource;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TodoResourceTest {

    @Test
    public void testCreateTodoEndpoint() {
        given()
          .body("{\"title\": \"Apprendre Quarkus\", \"description\": \"Terminer le tutoriel\"}")
          .header("Content-Type", "application/json")
          .when().post("/todos")
          .then()
             .statusCode(201)
             .body("title", is("Apprendre Quarkus"));
    }

    @Test
    public void testGetAllTodosEndpoint() {
        RestAssured.when().get("/todos")
          .then()
             .statusCode(200);
    }

    @Test
    public void testUpdateTodoStatus() {
        // Créer un nouveau Todo
        String todoId = given()
          .body("{\"title\": \"Faire du sport\", \"description\": \"Courir 5 km\"}")
          .header("Content-Type", "application/json")
          .when().post("/todos")
          .then()
             .statusCode(201)
             .extract().path("id").toString(); // Récupérer l'ID du Todo créé

        // Mettre à jour le statut du Todo créé
        given()
          .body("{\"completed\": true}")
          .header("Content-Type", "application/json")
          .when().put("/todos/" + todoId)  // Utiliser l'ID du Todo créé
          .then()
             .statusCode(200)
             .body("completed", is(true));
    }
}
