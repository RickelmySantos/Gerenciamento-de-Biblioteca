import { InjectionToken } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { DataStore } from 'src/app/core/store/data.store';

export interface MenuState {
    model: MenuItem[];
}

export const MENU_STATE = new InjectionToken<DataStore<MenuState>>('MenuState', { providedIn: 'root', factory: () => new DataStore<MenuState>() });
