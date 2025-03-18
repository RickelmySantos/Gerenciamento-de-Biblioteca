import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, Input } from '@angular/core';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { Livro } from 'src/app/models/livro.model';
import { SharedModule } from 'src/app/shared/shared.module';
import { ButtonActionsComponent } from 'src/app/shared/util/actions/button-action.component';

@Component({
    selector: 'app-card-footer',
    template: `
        <!-- ACTION BUTTONS -->

        <div>
            <app-button
                buttonClass="text-white"
                [contextLabel]="contextLabel"
                [ariaLabel]="'botao.visualizar'"
                [label]="'botao.visualizar'"
                [icon]="icons.core.visualizar"
                [type]="'link'"></app-button>
            <app-button buttonClass="text-white" [contextLabel]="contextLabel" [ariaLabel]="'botao.emprestimo'" [label]="'botao.emprestimo'" [icon]="icons.core.favoritos" [type]="'link'"></app-button>

            <ng-content></ng-content>
        </div>
    `,
    styles: [':host {display:block;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, ButtonActionsComponent],
})
export class CardFooterComponent extends RefreshableComponent {
    @Input()
    livro: Livro;

    @Input()
    contextLabel: string;
}
