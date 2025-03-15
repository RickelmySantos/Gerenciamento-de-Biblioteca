import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Livro } from 'src/app/models/livro.model';

@Injectable({ providedIn: 'root' })
export class LivroService {
    private readonly API_URL = 'http://localhost:8080/api/livros';

    constructor(private http: HttpClient) {}

    buscarLivros(): Observable<Livro[]> {
        return this.http.get<Livro[]>(`${this.API_URL}`);
    }
}
