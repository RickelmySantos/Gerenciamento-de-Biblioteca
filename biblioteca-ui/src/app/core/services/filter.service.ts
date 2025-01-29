import { Injectable } from '@angular/core';
import {
  debounceTime,
  distinctUntilChanged,
  filter,
  map,
  Observable,
  ReplaySubject,
} from 'rxjs';

@Injectable()
export class FilterService<T> {
  private item$: Observable<T[]>;
  private searchQueySubject = new ReplaySubject<string>(1);
  searchQuery$: Observable<string> = this.searchQueySubject.asObservable();

  setSearchQuery(query: string) {
    this.searchQueySubject.next(query);
  }

  filterItems(
    items: T[],
    filterFn: (item: T, query: string) => boolean
  ): Observable<T[]> {
    return this.searchQuery$.pipe(
      map((value) => value.trim()),
      filter((value) => value.length > 1),
      debounceTime(300),
      distinctUntilChanged(),
      map((query) => items.filter((item) => filterFn(item, query)))
    );
  }
}
