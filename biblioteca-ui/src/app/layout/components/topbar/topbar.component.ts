import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, ElementRef, inject, ViewChild } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { LayoutService } from 'src/app/layout/service/layout.service';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-topbar',
    templateUrl: './topbar.component.html',
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, NgIf, AsyncPipe],
})
export class TopbarComponent extends BaseComponent {
    protected readonly layoutService: LayoutService = inject(LayoutService);

    @ViewChild('toggleSidebarButton') readonly toggleSidebarButton: ElementRef;

    keyPressed(event: KeyboardEvent): void {
        if (event.code === 'Space' || event.code === 'Enter') {
            event.preventDefault();
            this.layoutService.toogleSidebar();
        }
    }
}
