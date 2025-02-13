import { Entity } from 'src/app/core/models/entity.model';
import { Livro } from 'src/app/models/livro.model';

export interface Autor extends Entity<number> {
    id: number;
    nome: string;
    sobrenome: string;
    nacionalidade: string;
    dataNascimento: Date;
    livro: Livro[];
}
