import { Entity } from 'src/app/core/models/entity.model';

export interface Livro extends Entity<number> {
    id: number;
    titulo: string;
    editora: string;
    idioma: string;
    genero: string;
    descricao: string;
}
