import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, inject } from '@angular/core';
import { map, Observable } from 'rxjs';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Livro } from 'src/app/models/livro.model';
import { BibliotecaCategoriasViewComponent } from 'src/app/modules/biblioteca/layout/components/biblioteca-categoria/biblioteca-categorias-view.component';
import { LivroService } from 'src/app/services/livro.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { CardRenderComponent } from 'src/app/shared/util/card/card-render.component';

@Component({
    selector: 'app-biblioteca-layout',
    templateUrl: './biblioteca-layout.component.html',
    styles: [':host {display: block; width: 100%; height: 100%;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, BibliotecaCategoriasViewComponent, CardRenderComponent, NgIf, AsyncPipe],
})
export class BibliotecaLayoutComponent extends BaseComponent {
    readonly livroService: LivroService = inject(LivroService);
    livros$: Observable<Livro[]>;

    override ngOnInit(): void {
        this.carregarLivros();
    }

    private carregarLivros() {
        this.livros$ = this.livroService.listAll().pipe(map(res => res.content));
    }
}
