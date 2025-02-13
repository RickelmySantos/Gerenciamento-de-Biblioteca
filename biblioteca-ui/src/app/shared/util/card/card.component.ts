import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, HostListener, Input } from '@angular/core';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { Livro } from 'src/app/models/livro.model';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-card',
    template: `
        <!-- <img src="https://primefaces.org/cdn/primeng/images/card-ng.jpg" alt="imagem do primeng" class="shadow-2 border-round-xl" /> -->
        <article class="card-content">
            <img src="assets/images/clean-code.png" alt="iamgem" class="shadow-2 border-round-xl" />

            <section class="info">
                <h3>{{ hover ? livro?.titulo + ' - ' + livro?.titulo : livro?.editora }}</h3>
                <p class="text-lg">{{ hover ? (livro?.descricao | slice : 0 : 60) : (livro?.descricao | slice : 0 : 40) }}</p>
                <footer class="buttons">
                    <ng-container>
                        <ng-content select="[ft-buttons]"></ng-content>
                    </ng-container>
                </footer>
            </section>
        </article>
    `,
    styleUrls: ['./card.component.scss'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, CommonModule],
})
export class CardComponent extends RefreshableComponent {
    @Input()
    rota: boolean;

    @Input()
    livro: Livro;

    hover = false;

    @HostListener('mouseenter')
    fullDescription() {
        this.hover = true;
    }
    @HostListener('mouseleave')
    limitedDescription() {
        this.hover = false;
    }
}
