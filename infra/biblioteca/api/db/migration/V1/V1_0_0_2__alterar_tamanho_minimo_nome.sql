ALTER TABLE tb_autores DROP CONSTRAINT IF EXISTS tb_autores_nome_check;
ALTER TABLE tb_autores ADD CONSTRAINT tb_autores_nome_check CHECK (LENGTH(nome) >= 3);

ALTER TABLE tb_autores DROP CONSTRAINT IF EXISTS tb_autores_sobrenome_check;
ALTER TABLE tb_autores ADD CONSTRAINT tb_autores_sobrenome_check CHECK (LENGTH(sobrenome) >=3);
