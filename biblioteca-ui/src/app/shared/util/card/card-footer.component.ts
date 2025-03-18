import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, Input, Output } from '@angular/core';
import { ActionButtonComponent } from 'src/app/core/components/crud/actions/action-button.component';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { Livro } from 'src/app/models/livro.model';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-card-footer',
    template: `
        <!-- ACTION BUTTONS -->
        <div>
            <action-button
                buttonClass="text-white"
                [contextLabel]="contextLabel"
                [ariaLabel]="'botao.visualizar'"
                [label]="'botao.visualizar'"
                [icon]="icons.core.visualizar"
                [type]="'link'"></action-button>
            <action-button buttonClass="text-white" [contextLabel]="contextLabel" [ariaLabel]="'botao.acessar'" [label]="'botao.acessar'" [icon]="icons.core.login" [type]="'link'"></action-button>
        </div>
    `,
    styles: [':host {display:block;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, ActionButtonComponent],
})
export class CardFooterComponent extends RefreshableComponent {
    @Input()
    livro: Livro;

    @Input()
    contextLabel: string;

    @Output()
    refreshButton = new EventEmitter<boolean>();
}
