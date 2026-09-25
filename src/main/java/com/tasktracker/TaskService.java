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
        tasks.remove(id);
    }
}
