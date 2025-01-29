import { NgClass, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';
import { IconProp } from '@fortawesome/fontawesome-svg-core';
import { ButtonModule } from 'primeng/button';
import { RippleModule } from 'primeng/ripple';
import { TooltipModule } from 'primeng/tooltip';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { SharedModule } from 'src/app/shared/shared.module';

type ActionButtonType = 'link' | 'header' | 'custom';

@Component({
    selector: 'action-button',
    template: `
        <ng-container>
            <button
                pButton
                pRipple
                [class]="buttonClass"
                [ngClass]="{ 'p-button-raised': isButtonHeader(), 'p-button-sm p-button-rounded outlined ': isButtonLink() }"
                [pTooltip]="label | translate"
                attr.aria-label="{{ ariaLabel ?? label | translate }} {{ contextLabel | translate }}"
                attr.aria-controls="{{ ariaControls }}"
                attr.aria-expanded="{{ ariaExpanded }}"
                tooltipPosition="bottom">
                <fa-icon *ngIf="icon" [icon]="icon" size="lg"></fa-icon>
                <!-- <span>{{ label | translate }}</span> -->
            </button>
        </ng-container>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, ButtonModule, RippleModule, TooltipModule, NgClass, NgIf],
})
export class ActionButtonComponent extends RefreshableComponent {
    @Input()
    preventDefault: boolean = false;

    @Input()
    label: string;

    @Input()
    ariaLabel: string;

    @Input()
    ariaControls: string;

    @Input()
    ariaExpanded: boolean = false;

    @Input()
    contextLabel: string;

    @Input()
    buttonClass: string;

    @Input()
    icon: IconProp;

    @Input()
    type: ActionButtonType = 'custom';

    isButtonLink(): boolean {
        return this.type === 'link';
    }

    isButtonCustom(): boolean {
        return this.type === 'custom';
    }

    isButtonHeader(): boolean {
        return this.type === 'header';
    }

    @Output()
    onClick = new EventEmitter<void>();

    override ngOnDestroy(): void {
        super.ngOnDestroy();
        this.onClick.unsubscribe();
    }

    click(event: Event): void {
        if (this.preventDefault) {
            event?.preventDefault();
        }
        this.onClick.emit();
    }
}
