import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { distinctUntilChanged, Observable, shareReplay, take } from 'rxjs';
import { Entity } from 'src/app/core/models/entity.model';
import { ResultList } from 'src/app/core/pagination/result-list';
import { environment } from 'src/environments/environment';

@Injectable()
export abstract class QueryService<E extends Entity<any>> {
    protected API: string = environment.apiUrl;
    protected abstract PATH: string;
    protected http: HttpClient = inject(HttpClient);

    protected getURl(): string {
        return `${this.API}/${this.PATH}`;
    }

    listAll(): Observable<ResultList<E>> {
        return this.http.get<ResultList<E>>(this.getURl()).pipe(distinctUntilChanged(), take(1), shareReplay());
    }

    load(id: number): Observable<ResultList<E[]>> {
        return this.http.get<ResultList<E[]>>(`${this.PATH}/${id}`).pipe(distinctUntilChanged(), take(1), shareReplay());
    }
    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.PATH}/${id}`).pipe(distinctUntilChanged(), take(1), shareReplay());
    }
}
