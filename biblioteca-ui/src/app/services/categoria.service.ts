import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Categoria, mapCategoria } from 'src/app/models/categoria.model';
const CATEGORIAS_MOCK_URL = 'assets/mocks/categorias.model.json';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
    constructor(private http: HttpClient) {}

    categoriaMocked(): Observable<Categoria[]> {
        return this.http.get<{ content: Categoria[] }>(CATEGORIAS_MOCK_URL).pipe(map(res => res.content));
    }

    categoriaMockedList(): Observable<Categoria[]> {
        return this.http.get<{ content: Categoria[] }>(CATEGORIAS_MOCK_URL).pipe(map(res => res.content.map(mapCategoria)));
    }
}
