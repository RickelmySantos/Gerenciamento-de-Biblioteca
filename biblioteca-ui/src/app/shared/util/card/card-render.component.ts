import { NgFor, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, Input, Output } from '@angular/core';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { Livro } from 'src/app/models/livro.model';
import { SharedModule } from 'src/app/shared/shared.module';
import { CardFooterComponent } from 'src/app/shared/util/card/card-footer.component';
import { CardComponent } from 'src/app/shared/util/card/card.component';

@Component({
    selector: 'app-card-render',
    template: `
        <section class="flex flex-column mt-3 mb-4">
            <header class=" flex flex-1 gap-4 align-content-center">
                <h2 class="text-4xl font-bold mb-5 ml-1">{{ title }}</h2>
                <ng-content></ng-content>
            </header>

            <ng-container *ngIf="data?.length; else noItems">
                <div class="flex flex-wrap gap-6 ml-4 ">
                    <ng-container *ngFor="let data of data; let i = index">
                        <app-card [livro]="data" (click)="onSelectLivro(data)">
                            <ng-container ft-buttons>
                                <app-card-footer [livro]="data"></app-card-footer>
                            </ng-container>
                        </app-card>
                    </ng-container>
                </div>
            </ng-container>
            <ng-template #noItems>
                <p class="text-black-alpha-90">{{ emptyMessage }}</p>
            </ng-template>
        </section>
    `,
    styles: [':host { display: block; }'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, NgIf, NgFor, CardComponent, CardFooterComponent],
})
export class CardRenderComponent extends RefreshableComponent {
    @Input()
    title: string;

    @Input()
    data: Livro[];

    @Input()
    hiddenButton: boolean = false;

    @Input()
    emptyMessage: string = 'Nenhum Livro encontrado!';

    @Output()
    selectLivro = new EventEmitter<Livro>();

    onSelectLivro(livro: Livro): void {
        this.selectLivro.emit(livro);
    }
}
