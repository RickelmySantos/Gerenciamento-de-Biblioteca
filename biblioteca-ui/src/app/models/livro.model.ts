import { Entity } from 'src/app/core/models/entity.model';
import { Autor } from 'src/app/models/autor.model';
import { Categoria } from 'src/app/models/categoria.model';

export interface Livro extends Entity<number> {
    id: number;
    titulo: string;
    editora: string;
    idioma: string;
    genero: string;
    descricao: string;
    autor: Autor;
    categoria: Categoria;
}
