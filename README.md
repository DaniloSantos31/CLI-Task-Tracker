# Task Tracker CLI

https://github.com/DaniloSantos31/CLI-Task-Tracker.git

Uma aplicação de linha de comando (CLI) desenvolvida em **Java** para criar, atualizar, excluir e acompanhar tarefas.

Este projeto foi desenvolvido com o objetivo de praticar conceitos fundamentais de programação e Java, incluindo **Programação Orientada a Objetos, manipulação de arquivos, argumentos de linha de comando, coleções, enums, tratamento de erros e persistência de dados em JSON**.

## Funcionalidades

*  Adicionar uma nova tarefa
*  Listar todas as tarefas
*  Atualizar uma tarefa
*  Excluir uma tarefa
*  Marcar tarefa como `in-progress`
*  Marcar tarefa como `done`
*  Listar tarefas `todo`
*  Listar tarefas `in-progress`
*  Listar tarefas `done`
* Persistir tarefas em um arquivo JSON


## Tecnologias

* **Java**
* **Java Collections**
* **Java NIO / File System**
* **JSON**
* **CLI (Command Line Interface)**
* **Git**

O projeto não utiliza frameworks ou bibliotecas externas.

## Status das tarefas

| Status        | Descrição                 |
| ------------- | ------------------------- |
| `todo`        | Tarefa ainda não iniciada |
| `in-progress` | Tarefa em andamento       |
| `done`        | Tarefa concluída          |

## Estrutura de uma tarefa

```text
id
description
status
createdAt
updatedAt
```

Exemplo:

```json
{
  "id": 1,
  "description": "Estudar Java",
  "status": "todo",
  "createdAt": "2026-09-25T15:00:00",
  "updatedAt": "2026-09-25T15:00:00"
}
```


## Persistência

As tarefas serão armazenadas no arquivo:

```text
tasks.json
```

Esse arquivo ficará no diretório atual do projeto.

Exemplo:

```text
task-tracker/
├── src/
├── tasks.json
├── README.md
└── .gitignore
```

Caso o arquivo `tasks.json` ainda não exista, a aplicação deverá criá-lo automaticamente.


Este projeto foi baseado no desafio **Task Tracker CLI** e possui algumas restrições:

* Deve ser executado através da linha de comando.
* Deve utilizar argumentos posicionais.
* As tarefas devem ser armazenadas em um arquivo JSON.
* O arquivo deve ser criado automaticamente caso não exista.
* Deve utilizar o sistema de arquivos nativo do Java.
* Não devem ser utilizadas bibliotecas ou frameworks externos.
* O programa deve tratar erros e casos extremos adequadamente.
