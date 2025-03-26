CREATE TABLE tb_categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

ALTER TABLE tb_livros DROP COLUMN categoria;
ALTER TABLE tb_livros ADD COLUMN categoria_id BIGINT;
ALTER TABLE tb_livros ADD CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES tb_categoria(id);