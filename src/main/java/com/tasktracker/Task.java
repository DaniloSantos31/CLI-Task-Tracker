package com.tasktracker;

import java.time.Instant;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String description;
    private TaskStatus status;
    private Instant createAt;
    private Instant updatedAt;

    public Task(String description) {
        this.id = idCounter++;
        this.description = description;
        this.status = TaskStatus.DONE;
        this.createAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description){
        this.description = description;
        this.updatedAt = Instant.now();
    }
    public String getStatus() {
        return status.toString();
    }
    public void setStatus(TaskStatus status){
        this.status = status;
        this.updatedAt = Instant.now();
    }
    public Instant getCreateAt() {
        return createAt;
    }
    public Instant getUpdateAt() {
        return updatedAt;
    }
}
