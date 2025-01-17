# Sistema de Gerenciamento de Biblioteca

Este projeto é um sistema básico de gerenciamento de biblioteca desenvolvido com Spring Framework. Ele foi criado com o objetivo de aprimorar conceitos de programação, lógica e uso de ferramentas modernas no desenvolvimento backend.

---

## Funcionalidades

### 1. Gestão de Livros:

- Adicionar, atualizar, listar e remover livros.
- Informações de cada livro:
  - Título
  - Autor
  - Gênero
  - Ano de publicação
  - Quantidade em estoque

### 2. Gestão de Autores:

- Adicionar, listar e remover autores.
- Informações de cada autor:
  - Nome
  - Nacionalidade
  - Data de nascimento

### 3. Gestão de Usuários:

- Cadastro de usuários com informações:
  - Nome
  - E-mail
  - Senha
  - Tipo de usuário (admin ou cliente)
- Login com autenticação e autorização usando Spring Security.

### 4. Controle de Empréstimos:

- Realizar empréstimos e devoluções de livros.
- Validar a disponibilidade dos livros antes do empréstimo.
- Registrar a data de empréstimo e data de devolução.

### 5. Relatórios Simples:

- Listar livros emprestados por usuário.
- Listar livros mais emprestados.

---

## Tecnologias Utilizadas

- **Spring Boot** - Framework principal para o backend.
- **Spring Data JPA** - Gerenciamento de persistência e acesso ao banco de dados.
- **Spring Security** - Implementação de autenticação e autorização.
- **H2 Database** - Banco de dados em memória para testes iniciais.
- **Maven** - Gerenciador de dependências e build.
- **JUnit e Mockito** - Testes unitários e de integração.

---

## Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Java 17+
- Maven 3.8+
- IDE de sua preferência (IntelliJ, Eclipse, VsCode, etc.)

### Passos para Execução

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd nome-do-repositorio
   ```

3. Execute o projeto usando Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação no navegador:
   - URL padrão: [http://localhost:8080](http://localhost:8080)

---

## Endpoints REST

### Livros:

- **GET** `/api/livros`: Listar todos os livros.
- **POST** `/api/livros`: Adicionar um novo livro.
- **PUT** `/api/livros/{id}`: Atualizar um livro existente.
- **DELETE** `/api/livros/{id}`: Remover um livro.

### Autores:

- **GET** `/api/autores`: Listar todos os autores.
- **POST** `/api/autores`: Adicionar um novo autor.
- **DELETE** `/api/autores/{id}`: Remover um autor.

### Usuários:

- **POST** `/api/usuarios`: Cadastro de usuários.
- **POST** `/api/login`: Login e autenticação.

### Empréstimos:

- **POST** `/api/Emprestimo`: Realizar um empréstimo.
- **PUT** `/api/Emprestimo/{id}/devolucao`: Realizar a devolução de um empréstimo.

---

## Melhorias Futuras

- Implementar busca com filtros (título, autor, gênero).
- Adicionar paginação e ordenação nos endpoints de listagem.
- Criar notificações para devoluções atrasadas.
- Integração com um frontend (Angular)

---
