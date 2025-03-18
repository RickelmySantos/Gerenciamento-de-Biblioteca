import { NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { SizeProp } from '@fortawesome/fontawesome-svg-core';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-user-photo',
    template: ``,
    styles: [':host:{display: inline-block}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, NgIf],
})
export class UserPhotoComponent extends RefreshableComponent {
    @Input()
    userId: string;

    @Input()
    image: string;

    @Input()
    imagemPanelStyle: string = 'w-3rem h-3rem';

    @Input()
    imagemIconSize: SizeProp = 'lg';
}
