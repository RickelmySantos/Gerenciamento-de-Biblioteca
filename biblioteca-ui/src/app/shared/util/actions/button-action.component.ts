import { NgClass, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA, EventEmitter, Input, Output } from '@angular/core';
import { IconProp, SizeProp } from '@fortawesome/fontawesome-svg-core';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { SharedModule } from 'src/app/shared/shared.module';

type ButtonStyleType = 'link' | 'button' | 'custom';

@Component({
    selector: 'app-button',
    template: `
        <ng-container>
            <button
                pButton
                pRipple
                [class]="buttonClass"
                [ngClass]="{ 'p-button-link p-button-rounded cursor-pointer': isButtonStyleLink(), 'p-button-sm p-button-rounded cursor-pointer': isButtonStyleDefatult() }"
                [pTooltip]="label | translate"
                tooltipPosition="bottom"
                tooltipStyleClass="mt-3"
                (click)="click($event)"
                attr.aria-label="{{ ariaLabel ?? label | translate }}"
                attr.aria-controls="{{ ariaControls }}"
                attr.aria-expanded="{{ ariaExpanded }}">
                <fa-icon *ngIf="icon" [icon]="icon" [size]="size"></fa-icon>
                <ng-content></ng-content>
            </button>
        </ng-container>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, ButtonModule, TooltipModule, NgIf, NgClass],
})
export class ButtonActionsComponent extends RefreshableComponent {
    private _show: boolean;

    get show(): boolean {
        return this._show;
    }
    @Input()
    set show(value: boolean) {
        this._show = value;
    }

    @Input()
    buttonClass: string;

    @Input()
    contextLabel: string;

    @Input()
    label: string;

    @Input()
    ariaLabel: string;

    @Input()
    ariaControls: string;

    @Input()
    ariaExpanded: boolean = false;

    @Input()
    size: SizeProp = '2xl';

    @Input()
    icon: IconProp;

    @Input()
    type: ButtonStyleType = 'custom';

    @Output()
    onClick = new EventEmitter<void>();

    click(event: PointerEvent): void {
        event.preventDefault();
        this.onClick.emit();
    }

    isButtonStyleLink(): boolean {
        return this.type === 'link';
    }

    isButtonStyleDefatult(): boolean {
        return this.type === 'button';
    }

    isButtonStyleCustom(): boolean {
        return this.type === 'custom';
    }

    override ngOnDestroy(): void {
        super.ngOnDestroy();

        this.onClick.unsubscribe();
    }
}
