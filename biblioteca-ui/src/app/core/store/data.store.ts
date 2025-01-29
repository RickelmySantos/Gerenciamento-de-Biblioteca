import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';
@Injectable()
export class DataStore<T> implements OnDestroy {
    private readonly data = new BehaviorSubject<T>({} as T);
    private readonly data$ = this.data.asObservable();

    private subscriptions: Subscription;

    constructor() {
        this.subscriptions = this.data$.subscribe();
    }

    set value(state: T) {
        this.data.next(state);
    }

    get value(): T {
        return this.data.getValue();
    }

    get value$(): Observable<T> {
        return this.data$;
    }

    ngOnDestroy(): void {
        if (this.subscriptions) {
            this.subscriptions.unsubscribe();
        }
    }
}
