SET search_path TO biblioteca;

-- Inserindo autores
INSERT INTO tb_autores (nome, sobrenome, nacionalidade, data_nascimento) VALUES
    ('Robert', 'Martin', 'Americano', '1952-12-05'),
    ('Martin', 'Fowler', 'Britânico', '1963-12-18'),
    ('Eric', 'Evans', 'Americano', '1960-11-01'),
    ('Kent', 'Beck', 'Americano', '1961-03-31'),
    ('Steve', 'McConnell', 'Americano', '1962-09-10'),
    ('Andrew', 'Hunt', 'Americano', '1965-07-15'),
    ('David', 'Thomas', 'Americano', '1966-02-06'),
    ('Fred', 'Brooks', 'Americano', '1931-04-19'),
    ('Donald', 'Knuth', 'Americano', '1938-01-10');

-- Inserindo livros relacionados a desenvolvimento de software
INSERT INTO tb_livros (titulo, editora, idioma, genero, autor_id) VALUES
    ('Código Limpo: Habilidades Práticas do Agile Software', 'Alta Books', 'Português', 'Engenharia de Software',
        (SELECT id FROM tb_autores WHERE nome = 'Robert' AND sobrenome = 'Martin')),
    ('Arquitetura Limpa: O Guia do Artesão para Estrutura e Design de Software', 'Alta Books', 'Português', 'Engenharia de Software',
        (SELECT id FROM tb_autores WHERE nome = 'Robert' AND sobrenome = 'Martin')),
    ('Refactoring: Improving the Design of Existing Code', 'Addison-Wesley', 'Inglês', 'Refatoração',
        (SELECT id FROM tb_autores WHERE nome = 'Martin' AND sobrenome = 'Fowler')),
    ('Domain-Driven Design: Tackling Complexity in the Heart of Software', 'Addison-Wesley', 'Inglês', 'Design de Software',
        (SELECT id FROM tb_autores WHERE nome = 'Eric' AND sobrenome = 'Evans')),
    ('Extreme Programming Explained: Embrace Change', 'Addison-Wesley', 'Inglês', 'Metodologias Ágeis',
        (SELECT id FROM tb_autores WHERE nome = 'Kent' AND sobrenome = 'Beck')),
    ('Code Complete: A Practical Handbook of Software Construction', 'Microsoft Press', 'Inglês', 'Engenharia de Software',
        (SELECT id FROM tb_autores WHERE nome = 'Steve' AND sobrenome = 'McConnell')),
    ('The Pragmatic Programmer: Your Journey to Mastery', 'Addison-Wesley', 'Inglês', 'Boas Práticas de Programação',
        (SELECT id FROM tb_autores WHERE nome = 'Andrew' AND sobrenome = 'Hunt')),
    ('The Pragmatic Programmer: Your Journey to Mastery', 'Addison-Wesley', 'Inglês', 'Boas Práticas de Programação',
        (SELECT id FROM tb_autores WHERE nome = 'David' AND sobrenome = 'Thomas')),
    ('The Mythical Man-Month: Essays on Software Engineering', 'Addison-Wesley', 'Inglês', 'Gestão de Projetos',
        (SELECT id FROM tb_autores WHERE nome = 'Fred' AND sobrenome = 'Brooks')),
    ('The Art of Computer Programming', 'Addison-Wesley', 'Inglês', 'Algoritmos',
        (SELECT id FROM tb_autores WHERE nome = 'Donald' AND sobrenome = 'Knuth'));
