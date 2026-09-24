package com.tasktracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        TaskService taskService = new TaskService();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        System.out.println("Welcome to TaskTracker");

        while(run) {
            System.out.println("Digite um comando: add[text], remove[id], list, exit.");
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
                    taskService.addTask(parts[1]);
                    System.out.println("Tarefa adicionada com sucesso! Que demais!");
                    break;
                case "list":
                    // listar tarefas
                    break;
                case "delete":
                    //deletar tarefas
                    break;
                case "exit":
                    run = false;
                    break;
                default:
                    System.out.println("Comando desconhecido: tente add, list ou delete.");
            }
        }
        scanner.close();
    }
}
