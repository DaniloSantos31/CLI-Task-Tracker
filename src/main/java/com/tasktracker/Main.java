package com.tasktracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        System.out.println("Welcome to TaskTracker");

        while(run) {
            System.out.println("Digite um comando: add [text], delete [id], status [id] [status], list [status], exit.");
            String input = scanner.nextLine().trim();
            if(input.isEmpty()){
                continue;
            }
            String[] parts = input.split(" ", 2);
            String command = parts[0];

            switch (command) {
                case "add":
                    if (parts.length < 2 || parts[1].trim().isEmpty()) {
                        System.out.println("Erro, informe a descrição da tarefa.");
                        break;
                    }
                    Task task = taskService.addTask(parts[1]);
                    System.out.println("Tarefa adicionada com sucesso! Que demais! (ID:" + task.getId() + ")");
                    break;
                case "list":
                    if(parts.length == 1) {
                        taskService.listTasks();
                        break;
                    }

                    try{
                        TaskStatus status = TaskStatus.valueOf(parts[1].trim().toUpperCase());
                        taskService.listTasksByStatus(status);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Status inválido. Use: TODO, IN_PROGRESS ou DONE");
                    }
                    break;
                case "delete":
                    if(parts.length < 2 || parts[1].trim().isEmpty()){
                        System.out.println("Informe o ID da tarefa");
                        break;
                    }
                    int id = Integer.parseInt(parts[1]);
                    taskService.deleteTask(id);
                    break;
                case "status":
                    if (parts.length < 2) {
                        System.out.println("Use: status [id] [TODO|IN_PROGRESS|DONE]");
                        break;
                    }

                    String[] statusParts = parts[1].trim().split("\\s+");

                    if (statusParts.length != 2) {
                        System.out.println("Use: status [id] [TODO|IN_PROGRESS|DONE]");
                        break;
                    }

                    try {
                        int taskId = Integer.parseInt(statusParts[0]);
                        TaskStatus status = TaskStatus.valueOf(statusParts[1].toUpperCase());

                        taskService.updateTaskStatus(taskId, status);

                    } catch (NumberFormatException e) {
                        System.out.println("O ID deve ser um número.");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Status inválido. Use TODO, IN_PROGRESS ou DONE.");
                    }

                    break;
                case "exit":
                    System.out.println("Encerrando o programa...");
                    run = false;
                    break;
                default:
                    System.out.println("Comando desconhecido: tente add, list ou delete.");
            }
        }
        scanner.close();
    }
}
