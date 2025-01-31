import { CUSTOM_ELEMENTS_SCHEMA, Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { BaseComponent } from 'src/app/core/util/base.component';
import { ErrorDetailsComponent } from 'src/app/layout/pages/error/components/error-details.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-unavailable-server',
    template: `
        <div class="h-full flex justify-content-center align-items-center">
            <div class="w-8 md:w-5">
                <app-error-details
                    image="unavailable"
                    code="500"
                    [title]="'mensagem.error.500.titulo' | translate"
                    [description]="'mensagem.error.500.mensagem' | translate"
                ></app-error-details>
            </div>
        </div>
    `,
    styles: [':host {display: block; height: 100%; }'],
    standalone: true,
    imports: [RippleModule, ButtonModule, ErrorDetailsComponent, SharedModule],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class UnavailableServerComponent extends BaseComponent {}
