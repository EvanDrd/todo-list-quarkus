package org.example.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Todo extends PanacheEntity{

    @Column(nullable = false)
    public String title;
    
    public String description;
    
    @Column(nullable = false)
    public boolean completed;
    
    // Constructeur vide pour Hibernate
    public Todo() {}
    
    public Todo(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;

    }
}
