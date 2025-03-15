import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Categoria } from 'src/app/models/categoria.model';
import { BibliotecaCategoriaComponent } from 'src/app/modules/biblioteca/layout/components/biblioteca-categoria/biblioteca-categoria.component';
import { CategoriaService } from 'src/app/services/categoria.service';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-biblioteca-categorias-card',
    template: `
        <ng-container *ngIf="categoriasMocked | async as result">
            <app-biblioteca-categoria *ngFor="let categoria of result" [categoria]="categoria" [isSelecionada]="categoria.id === categoriaSelecionadaId"></app-biblioteca-categoria>
        </ng-container>
    `,
    styles: [':host { display: inline-flex; width: 100%; gap: 1.6rem; flex-wrap: wrap;  justify-content: center; margin-right: 4rem; }'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, AsyncPipe, NgFor, NgIf, BibliotecaCategoriaComponent],
})
export class BibliotecaCategoriasCardComponent extends BaseComponent {
    readonly service: CategoriaService = inject(CategoriaService);
    categoriasMocked: Observable<Categoria[]>;
    @Output() categoriaSelecionada = new EventEmitter<Categoria | null>();
    @Input() categoriaSelecionadaId: number | null = null;

    override ngOnInit(): void {
        this.categoriasMocked = this.service.categoriaMockedList();
    }

    toggleCategoria(categoria: Categoria): void {
        if (this.categoriaSelecionadaId === categoria.id) {
            this.categoriaSelecionadaId = null;
            this.categoriaSelecionada.emit(null);
        } else {
            this.categoriaSelecionadaId = categoria.id;
            this.categoriaSelecionada.emit(categoria);
        }
    }
}
