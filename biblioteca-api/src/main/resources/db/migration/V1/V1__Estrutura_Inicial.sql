-- V1__Initial_Setup.sql

-- Criação da tabela tb_autores
CREATE TABLE tb_autores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL CHECK (char_length(nome) >= 5),
    sobrenome VARCHAR(100) NOT NULL CHECK (char_length(sobrenome) >= 5),
    nacionalidade VARCHAR NOT NULL,
    data_nascimento DATE NOT NULL
);

-- Criação da tabela tb_livros
CREATE TABLE tb_livros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL CHECK (char_length(titulo) >= 5),
    editora VARCHAR(100) NOT NULL CHECK (char_length(editora) >= 5),
    idioma VARCHAR(100),
    genero VARCHAR(100),
    autor_id BIGINT NOT NULL,
    CONSTRAINT fk_livros_autor FOREIGN KEY (autor_id) REFERENCES tb_autores (id) ON DELETE CASCADE
);

-- Criação da tabela tb_usuario
CREATE TABLE tb_usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL CHECK (char_length(nome) >= 5),
    email VARCHAR(255),
    senha VARCHAR(255),
    tipo_usuario VARCHAR(50) NOT NULL CHECK (tipo_usuario IN ('ADMINISTRADOR', 'GESTOR', 'USUARIO'))
);

-- Criação da tabela tb_emprestimo
CREATE TABLE tb_emprestimo (
    id SERIAL PRIMARY KEY,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    livros_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_emprestimo_livros FOREIGN KEY (livros_id) REFERENCES tb_livros (id) ON DELETE CASCADE,
    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario (id) ON DELETE CASCADE
);

-- Inserir dados iniciais para tb_autores
INSERT INTO tb_autores (nome, sobrenome, nacionalidade, data_nascimento) VALUES
('Joanne', 'Rowling', 'Britânica', '1965-07-31'),
('George', 'Orwell', 'Britânico', '1903-06-25'),
('Janes', 'Austen', 'Britânica', '1775-12-16');

-- -- Inserir dados iniciais para tb_livros
INSERT INTO tb_livros (titulo, editora, idioma, genero, autor_id) VALUES
('Harry Potter e a Pedra Filosofal', 'Rocco', 'Português', 'Fantasia', 1),
('19844', 'Companhia das Letras', 'Português', 'Distopia', 2),
('Orgulho e Preconceito', 'Penguin Classics', 'Inglês', 'Romance', 3);

-- -- Inserir dados iniciais para tb_usuario
INSERT INTO tb_usuario (nome, email, senha, tipo_usuario) VALUES
('Alice Silva', 'alice.silva@example.com', 'senha123', 'USUARIO'),
('Carlos Mendes', 'carlos.mendes@example.com', 'senha456', 'GESTOR'),
('Joana Almeida', 'joana.almeida@example.com', 'senha789', 'ADMINISTRADOR');

-- -- Inserir dados iniciais para tb_emprestimo
INSERT INTO tb_emprestimo (data_emprestimo, data_devolucao, livros_id, usuario_id) VALUES
('2025-01-01', '2025-01-15', 4, 1),
('2025-01-05', '2025-01-20', 5, 2),
('2025-01-10', '2025-01-25', 6, 3);
