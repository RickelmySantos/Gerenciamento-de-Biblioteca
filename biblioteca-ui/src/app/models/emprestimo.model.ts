import { Entity } from 'src/app/core/models/entity.model';

export interface Emprestimo extends Entity<number> {
    livroId: number;
    usuarioId: number;
}
