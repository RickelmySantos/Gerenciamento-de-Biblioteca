import { NgClass, NgStyle } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, Input, Output } from '@angular/core';
import { Categoria } from 'src/app/models/categoria.model';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-biblioteca-categoria',
    template: `
        <div
            [ngStyle]="{ 'background-color': categoria.cor }"
            class="flex flex-shrink-0 align-items-center justify-content-center px-5 border-round-3xl shadow-1 cursor-pointer w-20rem h-8rem"
            [ngClass]="classesCategoria"
            (click)="onSelecionada()">
            <span class="sm:text-lg md:text-xl xl:text-2xl">
                {{ categoria.nome }}
                <fa-icon [icon]="iconeCategoria" size="lg"></fa-icon>
            </span>
        </div>
    `,
    styles: [``],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, NgStyle, NgClass],
})
export class BibliotecaCategoriaComponent {
    @Input() categoria!: Categoria;
    @Input() isSelecionada = false;
    @Output() selecionada = new EventEmitter<void>();

    get classesCategoria() {
        return {
            'bg-blue-50': !this.isSelecionada,
            'categoria-selecionada shadow-6': this.isSelecionada,
        };
    }

    get iconeCategoria() {
        return this.categoria.icon();
    }

    onSelecionada(): void {
        this.selecionada.emit();
    }
}
