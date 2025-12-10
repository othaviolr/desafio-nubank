# API de Gerenciamento de Clientes e Contatos

Sistema simples pra gerenciar clientes e seus contatos usando Spring Boot e PostgreSQL.

## O que faz

- Cadastra clientes
- Adiciona contatos pros clientes
- Lista todos os clientes com seus contatos
- Busca contatos de um cliente específico

## Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Estrutura do Projeto
```
src/main/java/
├── controller/     # Endpoints REST
├── service/        # Lógica de negócio
├── repository/     # Acesso ao banco
├── model/          # Entidades JPA
├── dto/            # Objetos de transferência
└── exception/      # Tratamento de erros
```

## Relacionamento

Cliente (1) ----> (*) Contato

Um cliente pode ter vários contatos, mas cada contato pertence a apenas um cliente.
