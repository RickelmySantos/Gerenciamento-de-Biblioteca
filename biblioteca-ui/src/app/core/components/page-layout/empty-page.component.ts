import { ChangeDetectionStrategy, Component } from '@angular/core';
import { SharedModule } from 'primeng/api';
import { FooterComponent } from 'src/app/layout/components/footer/footer.component';

@Component({
    selector: 'empty-page',
    template: `
        <div id="app-container" class="app-container sidebar--hidden statusbar--hidden topbar--hidden">
            <div class="page-container">
                <!-- PAGE CONTAINER-->
                <div class="content-wrapper">
                    <!-- CONTENT WRAPPER -->
                    <main class="content-container">
                        <ng-content></ng-content>
                    </main>
                    <!-- CONTENT CONTAINER -->
                    <app-footer></app-footer>
                </div>
            </div>
        </div>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, FooterComponent],
})
export class EmptyPageComponent {}
