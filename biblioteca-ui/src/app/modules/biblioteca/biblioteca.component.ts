import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-biblioteca',
    template: `
        <router-outlet />
    `,
    styles: [':host {display: block; width: 100%; height: 100%;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, RouterOutlet],
})
export class BibliotecaComponent extends BaseComponent {}
