import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CrudService } from 'src/app/core/services/crud.service';
import { Emprestimo } from 'src/app/models/emprestimo.model';

interface EmprestimoRequestDto {
    livroId: number;
    usuarioId: number;
}

@Injectable({ providedIn: 'root' })
export class EmprestimoService extends CrudService<Emprestimo> {
    protected override PATH: string = 'emprestimos';

    criarEmprestimo(requestDto: EmprestimoRequestDto): Observable<Emprestimo> {
        return this.http.post<Emprestimo>(this.getURl(), requestDto);
    }
}
