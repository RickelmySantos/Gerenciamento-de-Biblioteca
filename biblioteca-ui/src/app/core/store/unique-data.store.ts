import { Injectable, OnDestroy } from '@angular/core';
import isEqual from 'lodash.isequal';
import { BehaviorSubject, Observable, Subscription } from 'rxjs';

@Injectable()
export class UniqueDataStore<T> implements OnDestroy {
    private readonly data = new BehaviorSubject<T>({} as T);
    private readonly data$ = this.data.asObservable();

    private subscription: Subscription;

    constructor() {
        this.subscription = this.data$.subscribe();
    }

    get value(): T {
        return this.data.getValue();
    }

    get value$(): Observable<T> {
        return this.data$;
    }

    set value(state: T) {
        if (!isEqual(this.data.value, state)) this.data.next(state);
    }

    ngOnDestroy(): void {
        if (this.subscription) {
            this.subscription.unsubscribe();
        }
    }
}
