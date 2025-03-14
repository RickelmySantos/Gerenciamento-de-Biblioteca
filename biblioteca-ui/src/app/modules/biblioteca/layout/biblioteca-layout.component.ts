import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-biblioteca-layout',
    templateUrl: './biblioteca-layout.component.html',
    styles: [],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule],
})
export class BibliotecaLayoutComponent extends BaseComponent {}
