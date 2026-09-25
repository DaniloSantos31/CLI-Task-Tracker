package com.tasktracker;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    private List<Task> tasks;

    private TaskRepository repository;

    public TaskService() {
        repository = new TaskRepository();
        tasks = repository.load();
    }

    public Task addTask(String description){
        if(description == null || description.trim().isEmpty()){
            throw new IllegalArgumentException("A descrição da tarefa não pode ser vazia.");
        }
        Task newTask = new Task(description);
        tasks.add(newTask);
        repository.save(tasks);
        return newTask;
    }

    public void listTasks(){
        if(tasks.isEmpty()){
            System.out.println("Nenhuma tarefa encontrada");
            return;
        }
        System.out.println("--Lista de tarefas--");
        for(Task task : tasks){
            System.out.println(task);
        }
        System.out.println("---------------------");
    }

    public void deleteTask(int id){
        boolean removed = tasks.removeIf(task ->  task.getId() == id);

        if(!removed){
            throw new IllegalArgumentException("Tarefa não encontrada");
        }

        repository.save(tasks);
    }

    public void listTasksByStatus(TaskStatus status) {
        boolean found = false;

        System.out.println("-- Lista de tarefas: " + status + " --");

        for (Task task : tasks) {
            if (task.getStatus() == status) {
                System.out.println(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "Tarefa com status " + status + " não encontrada."
            );
        }

        System.out.println("---------------------");
    }

    public void updateTaskStatus(int id, TaskStatus status){
        for(Task task : tasks){
            if(task.getId() == id){
                task.setStatus(status);
                repository.save(tasks);

                System.out.println("Status da tarefa " + id + " alterado para " + status);
                return;
            }
        }
        throw new IllegalArgumentException("Tarefa com id:" + id + "não encontrada");
    }

    public void updateTask(int id, String description){
        for(Task task : tasks) {
            if (task.getId() == id) {
                task.setDescription(description);
                repository.save(tasks);

            System.out.println("Descrição atualizada!");
            return;
        }
        }
        throw new IllegalArgumentException("Tarefa com id: " + id + " não encontrada");
    }
}
