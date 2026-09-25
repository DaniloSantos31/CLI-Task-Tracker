package com.tasktracker;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public final Path filePath = Path.of("tasks.json");

    public void save(List<Task> tasks) {
        StringBuilder json = new StringBuilder();

        json.append("[\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            json.append(" {\n");
            json.append("   \"id\": ").append(task.getId()).append(",\n");
            json.append("    \"description\": \"")
                    .append(escapeJson(task.getDescription()))
                    .append("\"\n");
            json.append("    \"status\": \"")
                            .append(task.getStatus().name())
                            .append("\"\n");
            json.append("    \"createAt\": \"")
                    .append(task.getCreateAt())
                    .append("\"\n");
            json.append("    \"updateAt\": \"")
                    .append(task.getUpdateAt())
                    .append("\"\n");
            json.append("  }");

            if (i < tasks.size() - 1) {
                json.append(",");
            }

            json.append("\n");
        }
        json.append("]\n");

        try {
            Files.writeString(filePath, json.toString());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar tarefas ", e);
        }
        }

        public List<Task> load() {
        if(!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(filePath);
            return parseJson(json);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar tarefas ", e);
        }
    }

    private String escapeJson(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }

    private List<Task> parseJson(String json) {
        List<Task> tasks = new ArrayList<>();
        json = json.trim();

        if(json.equals("[]")) {
            return tasks;
        }

        json = json.substring(1, json.length() - 1).trim();

        String[] objects = json.split("\\},\\s*\\{");
        for  (String object : objects) {
            object = object
                    .replace("{", "")
                    .replace("]", "")
                    .trim();

            String[] fields = object.split(",\\s*");
            int id = 0;
            String description = "";
            TaskStatus status = null;
            LocalDateTime createdAt = null;
            LocalDateTime updatedAt = null;
            for (String field : fields ) {
                String[] keyValue = field.split(":", 2);

                String key = keyValue[0]
                        .trim()
                        .replace("\"", "");
                String value = keyValue[1].trim();

                if(key.equals("id")) {
                    id = Integer.parseInt(value);
                }
                if (key.equals("description")) {
                    description = value
                            .replace("\"", "");
                }
                if(key.equals("status")) {
                    status = TaskStatus.valueOf(value.replace("\"", ""));
                }
                if (key.equals("createdAt")) {
                    createdAt = LocalDateTime.parse(value.replace("\"",""));
                }
                if (key.equals("updatedAt")) {
                    updatedAt = LocalDateTime.parse(value.replace("\"", ""));
                }
            }
            tasks.add(new Task(id, description, status, createdAt, updatedAt));
        }
        return tasks;
    }
}
