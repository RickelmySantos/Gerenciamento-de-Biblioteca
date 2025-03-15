import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { BibliotecaCategoriasViewComponent } from 'src/app/modules/biblioteca/layout/components/biblioteca-categoria/biblioteca-categorias-view.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-biblioteca-layout',
    templateUrl: './biblioteca-layout.component.html',
    styles: [':host {display: block; width: 100%; height: 100%;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, BibliotecaCategoriasViewComponent],
})
export class BibliotecaLayoutComponent extends BaseComponent {}
