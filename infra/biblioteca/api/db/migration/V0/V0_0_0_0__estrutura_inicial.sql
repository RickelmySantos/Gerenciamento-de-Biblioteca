
CREATE TABLE IF NOT EXISTS tb_autores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL CHECK (char_length(nome) >= 5),
    sobrenome VARCHAR(100) NOT NULL CHECK (char_length(sobrenome) >= 5),
    nacionalidade VARCHAR NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_livros (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL CHECK (char_length(titulo) >= 5),
    editora VARCHAR(100) NOT NULL CHECK (char_length(editora) >= 5),
    idioma VARCHAR(100),
    genero VARCHAR(100),
    autor_id BIGINT NOT NULL,
    CONSTRAINT fk_livros_autor FOREIGN KEY (autor_id) REFERENCES tb_autores (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL CHECK (char_length(nome) >= 5),
    email VARCHAR(255),
    senha VARCHAR(255),
    tipo_usuario VARCHAR(50) NOT NULL CHECK (tipo_usuario IN ('ADMINISTRADOR', 'GESTOR', 'USUARIO'))
);

CREATE TABLE IF NOT EXISTS tb_emprestimo (
    id SERIAL PRIMARY KEY,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    livros_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT fk_emprestimo_livros FOREIGN KEY (livros_id) REFERENCES tb_livros (id) ON DELETE CASCADE,
    CONSTRAINT fk_emprestimo_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuario (id) ON DELETE CASCADE
);

