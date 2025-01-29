import { InjectionToken } from '@angular/core';
import { DataStore } from 'src/app/core/store/data.store';

export interface LayoutState {
    sidebarExpanded: boolean;
    sidebarCollapsed: boolean;
    sidebarVisible: boolean;
    sidebarPinned: boolean;
}

export const LAYOUT_STATE = new InjectionToken<DataStore<LayoutState>>('LayoutState', { providedIn: 'root', factory: () => new DataStore<LayoutState>() });
