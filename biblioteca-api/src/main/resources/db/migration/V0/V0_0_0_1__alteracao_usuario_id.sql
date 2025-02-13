ALTER TABLE tb_emprestimo DROP CONSTRAINT IF EXISTS fk_emprestimo_usuario;

ALTER TABLE tb_emprestimo ALTER COLUMN usuario_id TYPE VARCHAR(255) USING usuario_id::VARCHAR;

ALTER TABLE tb_usuario ALTER COLUMN id TYPE VARCHAR(255) USING id::VARCHAR;

ALTER TABLE tb_emprestimo ADD CONSTRAINT fk_emprestimo_usuario
FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id);
