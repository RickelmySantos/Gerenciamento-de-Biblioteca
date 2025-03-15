import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, Input } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { BibliotecaCategoriasCardComponent } from 'src/app/modules/biblioteca/layout/components/biblioteca-categoria/biblioteca-categorias-card.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-biblioteca-categorias-view',
    template: `
        <div class="flex justify-content-center text-4xl font-italic mb-6">{{ 'Aprecise nossa Coleção de livros' }}</div>
        <h2 class="text-4xl font-bold mb-5 ">
            {{ title }}
        </h2>
        <div class="categorias__content sm:flex-row md:flex-row">
            <app-biblioteca-categorias-card />
        </div>
    `,
    styles: [``],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, BibliotecaCategoriasCardComponent],
})
export class BibliotecaCategoriasViewComponent extends BaseComponent {
    @Input()
    title = 'Categorias';
}
