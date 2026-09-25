package com.tasktracker;

import java.time.LocalDateTime;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.id = idCounter++;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public Task(int id, String description,  TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        if(id>= idCounter) {
            idCounter = id + 1;
        }
    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status){
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    @Override
    public String toString() {
        return "ID: " + id
                + " | description: " + description
                + " | status: " + status
                + " | criado em: " + createdAt
                + " | atualizado em: " + updatedAt;
    }
}
