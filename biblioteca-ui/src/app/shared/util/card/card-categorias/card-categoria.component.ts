import { NgFor } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-card-categorias',
    template: `
        <section class="card-categoria-wrapper">
            <div class="wrapper-content" *ngFor="let categoria of categorias">
                <div class="cotent">
                    <h3 class="text-xl ">{{ categoria.nome }}</h3>
                    <p class="font-bold">{{ categoria.descricao }}</p>
                </div>
            </div>
        </section>
    `,
    styleUrls: ['./card-categorias.component.scss'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, NgFor],
})
export class CardCategoriasComponent extends BaseComponent {
    categorias = [
        { nome: 'Categoria 1', descricao: 'icon', imagem: 'url-da-imagem-1' },
        { nome: 'Categoria 2', descricao: 'icon', imagem: 'url-da-imagem-2' },
        // Adicione mais categorias conforme necessário
    ];
}
