import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, Input } from '@angular/core';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { Livro } from 'src/app/models/livro.model';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-card-footer',
    template: `
        <!-- ACTION BUTTONS -->
    `,
    styles: [':host:{display:block;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule],
})
export class CardFooterComponent extends RefreshableComponent {
    @Input()
    livro: Livro;
}
