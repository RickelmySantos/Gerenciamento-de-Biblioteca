import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LAYOUT_STATE, LayoutState } from 'src/app/core/layout/state/layout.state';
import { DataStore } from 'src/app/core/store/data.store';

enum ResponsiveBreakpoints {
    TABLET = 600,
    TABLET_LANDSCAPE = 900,
    NOTEBOOK = 1200,
    DESKTOP = 1800,
    TV = 2400,
}

@Injectable({ providedIn: 'root' })
export class LayoutService {
    private readonly store: DataStore<LayoutState> = inject(LAYOUT_STATE);
    private windowSizeStore: DataStore<string> = new DataStore();

    get state$(): Observable<LayoutState> {
        return this.store.value$;
    }

    get state(): LayoutState {
        return this.store.value;
    }

    get windowState$(): Observable<string> {
        return this.windowSizeStore.value$;
    }

    get windowState(): string {
        return this.windowSizeStore.value;
    }

    private updateWindowSizeState(): void {
        let windowsSize: string = '';

        if (this.isTablet()) windowsSize = 'sm';
        if (this.isTabletLandscape()) windowsSize = 'md';
        if (this.isNotebook()) windowsSize = 'lg';
        if (this.isDesktop()) windowsSize = 'xl';
        if (this.isTV()) windowsSize = 'xxl';

        if (this.windowSizeStore.value !== windowsSize) this.windowSizeStore.value = windowsSize;
    }

    resetLayout() {
        this.updateWindowSizeState();

        const newState = { ...this.state };

        if (this.isMobile()) {
            newState.sidebarVisible = false;
            newState.sidebarPinned = true;
            newState.sidebarExpanded = true;
        } else if (this.isTablet()) {
            newState.sidebarVisible = false;
            newState.sidebarPinned = false;
            newState.sidebarExpanded = true;
        } else {
            newState.sidebarVisible = true;
            newState.sidebarPinned = true;
            newState.sidebarExpanded = false;
        }

        this.store.value = newState;
    }

    isSidebarActivated(): boolean {
        return this.isMobile() ? this.state.sidebarVisible : this.state.sidebarExpanded;
    }

    isResponsiveBreakpoint(): boolean {
        return this.isMobile() || this.isTablet();
    }

    isMobile(): boolean {
        const windowWidth = window.innerWidth;
        return windowWidth < ResponsiveBreakpoints.TABLET;
    }

    isTablet(): boolean {
        const windowWidth = window.innerWidth;
        return windowWidth >= ResponsiveBreakpoints.TABLET && windowWidth < ResponsiveBreakpoints.TABLET_LANDSCAPE;
    }

    isTabletLandscape(): boolean {
        const windowWidth = window.innerWidth;
        return windowWidth >= ResponsiveBreakpoints.TABLET_LANDSCAPE && windowWidth < ResponsiveBreakpoints.NOTEBOOK;
    }

    isNotebook(): boolean {
        const windowWidth = window.innerWidth;
        return windowWidth >= ResponsiveBreakpoints.NOTEBOOK && windowWidth < ResponsiveBreakpoints.DESKTOP;
    }

    isDesktop(): boolean {
        const windowWidth = window.innerWidth;
        return windowWidth >= ResponsiveBreakpoints.DESKTOP && windowWidth < ResponsiveBreakpoints.TV;
    }

    isTV(): boolean {
        return window.innerWidth >= ResponsiveBreakpoints.TV;
    }

    tooglePinSidebar(): void {
        this.store.value = { ...this.state, sidebarPinned: !this.state.sidebarPinned };
    }

    toogleSidebar(): void {
        this.store.value = { ...this.state, sidebarVisible: !this.state.sidebarVisible };
    }

    pinSideba(): void {
        this.store.value = { ...this.state, sidebarPinned: true };
    }

    unpinSidebar(): void {
        this.store.value = { ...this.state, sidebarPinned: false };
    }

    showSidebar(): void {
        this.store.value = { ...this.state, sidebarVisible: true };
    }

    hideSidebar(): void {
        this.store.value = { ...this.state, sidebarVisible: false };
    }

    toggleExpandSidebar(): void {
        this.store.value = { ...this.state, sidebarExpanded: !this.state.sidebarExpanded };
    }

    expandSidebar(): void {
        if (this.isMobile()) {
            this.store.value = { ...this.state, sidebarVisible: true };
        } else {
            this.store.value = { ...this.state, sidebarExpanded: true };
        }
    }
    collapseSidebar(): void {
        if (this.isMobile()) {
            this.store.value = { ...this.state, sidebarVisible: false };
        } else {
            this.store.value = { ...this.state, sidebarExpanded: false };
        }
    }
}
