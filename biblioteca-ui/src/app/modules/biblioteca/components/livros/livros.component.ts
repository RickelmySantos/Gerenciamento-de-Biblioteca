import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Livro } from 'src/app/models/livro.model';
import { LivroService } from 'src/app/services/livroService.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { CardRenderComponent } from 'src/app/shared/util/card/card-render.component';

@Component({
    selector: 'app-livros',
    template: `
        <div class=" fluid-content border-1 bg-white">
            <section class="border-1">
                <h1 class="text-2xl">Recomendados</h1>
                <article>
                    <ng-container *ngIf="">
                        <app-card-render />
                    </ng-container>
                </article>
            </section>
            <section>teste2</section>
        </div>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, CardRenderComponent],
})
export class LivrosComponent extends BaseComponent {
    livros$: Observable<Livro[]>;

    constructor(private livroService: LivroService) {
        super();
    }

    override ngOnInit(): void {
        this.livros$ = this.livroService.buscarLivros();
        console.log('livros >>>>>', this.livros$.subscribe());
    }
}
