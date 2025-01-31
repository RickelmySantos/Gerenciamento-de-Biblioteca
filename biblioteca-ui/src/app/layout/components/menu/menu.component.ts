import { AsyncPipe, NgFor, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { BaseComponent } from 'src/app/core/util/base.component';
import { MenuService } from 'src/app/layout/service/menu.service';
import { SharedModule } from 'src/app/shared/shared.module';
import { MenuItemComponent } from '../menu-item/menu-item.component';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styles: [':host {width: 100%; margin-top: 0.5rem; margin-bottom: 0.5rem}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule, NgFor, NgIf, AsyncPipe, MenuItemComponent],
})
export class MenuComponent extends BaseComponent {
    constructor(public readonly menuService: MenuService) {
        super();
    }

    trackMenuItemById(index: number, menuItem: MenuItem): string {
        return menuItem.id;
    }
}
