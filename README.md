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

- **Backend**

  - Java - v.17
  - Spring Boot - v.3.0.6
  - Jakarta Persistence - v.3.1.0
  - Hibernate - v.6.1.7.Final
  - Flyway - v.9.5.1
  - Lombok - v.1.18.26
  - Map Struct - v.1.5.3.Final
  - **Testes**
    - JUnit - v.5.9.2 (Jupiter)

- **Frontend**

  - Angular - v.15
  - PrimeNG - v.15
  - RxJS - v.7.8
  - HTML - v.5
  - SASS / CSS
  - Javascript / TypeScript - ES2022
  - **Testes**
    - Karma - v.6.4
  - **UI Design**
    - PrimeFlex - v.15
    - Font Awesome - v.6.4
  - **Formatação e Análise Estática**
    - Prettier - v.2.8.3
    - Eslint - v.8.31.0

- **Banco de Dados**

  - Postgres - v.42.6.0 (Driver Version)

- **Autenticação e Autorização**

  - JWT / OAuth2
  - Keycloak - v.16.1.0

- **Dependências e Empacotamento**

  - Maven - v.3.9.1
  - NPM - v.9.5.1

- **Execução**

  - Tomcat - v.10.1.8
  - Node - v.18.16 (Localmente)
  - Apache - v.2.4

---

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

   - URL padrão: [http://localhost:8080]

5. Acesse swagger:
   - URL padrão: [http://localhost:8080/api/swagger-ui/index.html]

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

### Empréstimos:

- **POST** `/api/Emprestimo`: Realizar um empréstimo.
- **PUT** `/api/Emprestimo/{id}/devolucao`: Realizar a devolução de um empréstimo.

---

## Melhorias Futuras

- [] Implementar busca com filtros (título, autor, gênero).
- [x] Adicionar paginação e ordenação nos endpoints de listagem.
- [] Criar notificações para devoluções atrasadas.
- [x] Integração com um frontend (Angular)

---
