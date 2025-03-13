import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { map, Observable } from 'rxjs';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Livro } from 'src/app/models/livro.model';
import { LivroService } from 'src/app/services/livroService.service';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-livros',
    template: `
        <div class="flex w-full h-full"></div>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, ButtonModule],
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
