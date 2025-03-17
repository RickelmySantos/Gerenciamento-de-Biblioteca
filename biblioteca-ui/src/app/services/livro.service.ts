import { Injectable } from '@angular/core';
import { Observable, take } from 'rxjs';
import { CrudService } from 'src/app/core/services/crud.service';
import { Livro } from 'src/app/models/livro.model';

@Injectable({ providedIn: 'root' })
export class LivroService extends CrudService<Livro> {
    protected override PATH: string = 'livros';

    buscarLivros(): Observable<Livro[]> {
        return this.http.get<Livro[]>(this.getURl()).pipe(take(1));
    }
}
