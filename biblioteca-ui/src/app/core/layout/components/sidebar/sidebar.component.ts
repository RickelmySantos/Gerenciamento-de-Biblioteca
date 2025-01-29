import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
import { MenuComponent } from 'src/app/core/layout/components/menu/menu.component';
import { LayoutService } from 'src/app/core/layout/service/layout.service';
import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-sidebar',
    templateUrl: './sidebar.component.html',
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, NgIf, AsyncPipe, MenuComponent],
})
export class SidebarComponent extends BaseComponent {
    @Output() enter = new EventEmitter<boolean>();
    @Output() leave = new EventEmitter<boolean>();
    @Output() togglePin = new EventEmitter<boolean>();

    @ViewChild('sidebarMenu') readonly sidebarMenu!: ElementRef;
    @ViewChild(MenuComponent) readonly menuComponent!: MenuComponent;

    constructor(public readonly layoutService: LayoutService) {
        super();
    }

    mouseEnterSidebar(event: MouseEvent) {
        this.layoutService.expandSidebar();
        this.enter.emit(true);
    }

    mouseLeaveSidebar(event: MouseEvent) {
        this.layoutService.collapseSidebar();
        this.leave.emit(true);
    }

    togglePinSidebar(): void {
        this.togglePin.emit(true);
    }

    override ngOnDestroy(): void {
        super.ngOnDestroy();
        this.enter.unsubscribe();
        this.leave.unsubscribe();
        this.togglePin.unsubscribe();
    }
}
