import { ChangeDetectionStrategy, Component } from '@angular/core';

import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-home',
    template: `
        <section class="h-14rem flex flex-column justify-content-center align-items-start surface-section surface-border border-bottom-1 px-4 pt-4 pb-0">
            <div class="flex w-full h-full justify-content-between align-items-center">
                <div class="flex flex-1 align-items-center justify-content-start gap-3">
                    <div>
                        <!--- FOTO--->
                    </div>
                    <div class="flex flex-1 flex-column gap-2">
                        <h1 class="text-3xl lg:text-4xl font-extrabold m-0">{{ 'home.mensagem.cumprimento' | translate }} !</h1>
                        <div class="text-color-secondary font-medium">
                            <fa-icon [icon]="icons.home.alertaMensagemUsuario" class="lg"></fa-icon>
                            {{ userMessage }}
                        </div>
                    </div>
                </div>
            </div>
        </section>
    `,

    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule],
})
export class HomeComponent extends BaseComponent {
    get userMessage(): string {
        return this.translate.instant('home.mensagem.notificacao');
    }
}
