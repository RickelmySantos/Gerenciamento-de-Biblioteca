import { AsyncPipe, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DataStore } from 'src/app/core/store/data.store';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Livro } from 'src/app/models/livro.model';
import { BibliotecaCategoriasViewComponent } from 'src/app/modules/biblioteca/layout/components/biblioteca-categoria/biblioteca-categorias-view.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CardRenderComponent } from 'src/app/shared/util/card/card-render.component';

@Component({
    selector: 'app-biblioteca-layout',
    templateUrl: './biblioteca-layout.component.html',
    styles: [':host {display: block; width: 100%; height: 100%;}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [SharedModule, BibliotecaCategoriasViewComponent, CardRenderComponent, NgIf, AsyncPipe],
})
export class BibliotecaLayoutComponent extends BaseComponent {
    livroStore = new DataStore<Livro[]>();
}
