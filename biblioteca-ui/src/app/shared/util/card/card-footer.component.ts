import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, inject, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/core/auth/auth.service';
import { User } from 'src/app/core/modules/seguranca/model/user.model';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { Livro } from 'src/app/models/livro.model';
import { EmprestimoService } from 'src/app/services/emprestimo.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { ButtonActionsComponent } from 'src/app/shared/util/actions/button-action.component';

@Component({
    selector: 'app-card-footer',
    template: `
        <!-- ACTION BUTTONS -->

        <div>
            <app-button
                buttonClass="text-white"
                [contextLabel]="contextLabel"
                [ariaLabel]="'botao.visualizar'"
                [label]="'botao.visualizar'"
                [icon]="icons.core.visualizar"
                [type]="'link'"></app-button>
            <!-- <app-button
                buttonClass="text-white"
                [contextLabel]="contextLabel"
                [ariaLabel]="'botao.emprestimo'"
                [label]="'botao.emprestimo'"
                [icon]="icons.core.favoritos"
                [type]="'link'"
                (onClick)="solicitarEmprestimo()"></app-button> -->
            <app-button
                *ngIf="user$ | async as user"
                buttonClass="text-white"
                [contextLabel]="contextLabel"
                [ariaLabel]="'botao.emprestimo'"
                [label]="'botao.emprestimo'"
                [icon]="icons.core.favoritos"
                [type]="'link'"
                (click)="solicitarEmprestimo()"></app-button>

            <ng-content></ng-content>
        </div>
    `,
    styles: [':host {display:block;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, ButtonActionsComponent, AsyncPipe, NgIf],
})
export class CardFooterComponent extends RefreshableComponent {
    protected readonly authService = inject(AuthService);
    @Input()
    livro: Livro;

    @Input()
    contextLabel: string;

    user$: Observable<User>;
    user: any;

    constructor(private emprestimoService: EmprestimoService) {
        super();
    }
    override ngOnInit(): void {
        this.user$ = this.authService.user$;
        this.authService.user$.subscribe(user => {
            this.user = user;
            console.log('Usuário recebido:', JSON.stringify(user, null, 2));
        });
    }

    solicitarEmprestimo() {
        if (!this.livro || !this.livro.id) {
            console.error('Livro inválido!');
            return;
        }
        const request = {
            livroId: this.livro.id,
            usuarioId: this.user.id,
        };

        this.emprestimoService.criarEmprestimo(request).subscribe({
            next: res => {
                console.log('Empréstimo realizado com sucesso:', res);
                alert('Empréstimo realizado com sucesso!');
            },
            error: err => {
                console.error('Erro ao solicitar empréstimo:', err);
                alert('Erro ao solicitar empréstimo!');
            },
        });
    }
}
