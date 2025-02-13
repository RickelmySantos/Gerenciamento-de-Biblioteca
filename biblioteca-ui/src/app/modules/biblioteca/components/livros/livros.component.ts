import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { map, Observable } from 'rxjs';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Livro } from 'src/app/models/livro.model';
import { LivroService } from 'src/app/services/livroService.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { CardRenderComponent } from 'src/app/shared/util/card/card-render.component';

@Component({
    selector: 'app-livros',
    template: `
        <div class="flex w-full h-full ">
            <main class="fluid-content p-4 border-round-2xl flex flex-column gap-4">
                <section class="lista-livros  ">
                    <app-card-render [title]="'RECOMENDADOS'" [data]="livros$ | async" (selectLivro)="selecionarLivro($event)"></app-card-render>
                </section>

                <section class="lista-livros ">
                    <app-card-render [title]="'CATEGORIAS'" [data]="livros$ | async" (selectLivro)="selecionarLivro($event)"></app-card-render>
                </section>
            </main>

            <aside class="livros-detalhes" *ngIf="livroSelecionado">
                <div class="detalhes">
                    <img src="assets/images/clean-code.png" alt="{{ livroSelecionado.titulo }}" />
                    <h3 class="livro-titulo">{{ livroSelecionado.titulo }}</h3>
                    <p class="livro-autor">{{ livroSelecionado?.autor?.nome }}</p>

                    <div class="avaliacoes">
                        <span class="stars">⭐⭐⭐⭐⭐</span>
                        <span class="avaliacao">4.8</span>
                    </div>

                    <div class="stats">
                        <div class="stat">
                            <p class="stat-value">230</p>
                            <span class="stat-label">Páginas</span>
                        </div>
                        <div class="stat">
                            <p class="stat-value">4300</p>
                            <span class="stat-label">Avaliações</span>
                        </div>
                        <div class="stat">
                            <p class="stat-value">{{ livroSelecionado.idioma }}</p>
                        </div>
                    </div>
                </div>
                <p class="descricao">{{ livroSelecionado.descricao }}</p>
            </aside>
        </div>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, ButtonModule, AsyncPipe, NgIf, CardRenderComponent],
})
export class LivrosComponent extends BaseComponent {
    livros$: Observable<Livro[]>;
    livroSelecionado: Livro | null = null;

    constructor(private livroService: LivroService) {
        super();
    }

    override ngOnInit(): void {
        this.livros$ = this.livroService.buscarLivros().pipe(map(livros => livros.slice(0, 7)));
    }

    selecionarLivro(livro: Livro): void {
        this.livroSelecionado = livro;
    }
}
