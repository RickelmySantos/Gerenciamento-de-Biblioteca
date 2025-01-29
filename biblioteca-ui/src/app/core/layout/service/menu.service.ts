import { Inject, Injectable } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Observable } from 'rxjs';
import { MENU_STATE, MenuState } from 'src/app/core/layout/state/menu.state';
import { DataStore } from 'src/app/core/store/data.store';

@Injectable({ providedIn: 'root' })
export class MenuService {
    constructor(@Inject(MENU_STATE) private readonly store: DataStore<MenuState>) {}

    get state$(): Observable<MenuState> {
        return this.store.value$;
    }

    get stateValue(): MenuState {
        return this.store.value;
    }

    load(model: MenuItem[]): void {
        this.store.value = { model };
    }
}
