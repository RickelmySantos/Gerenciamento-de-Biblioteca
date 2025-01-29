import { NgClass, NgFor, NgIf } from '@angular/common';
import { ChangeDetectionStrategy, Component, forwardRef, inject, Input } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { IconLookup } from '@fortawesome/fontawesome-svg-core';
import { MenuItem } from 'primeng/api';
import { RippleModule } from 'primeng/ripple';
import { AuthService } from 'src/app/core/auth/auth.service';
import { BaseComponent } from 'src/app/core/util/base.component';
import { Permission } from 'src/app/shared/auth/permission.enum';
import { Role } from 'src/app/shared/auth/role.enum';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-menu-item',
    templateUrl: './menu-item.component.html',
    styles: [':host {width: 100%}'],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [NgIf, RippleModule, RouterLinkActive, RouterLink, NgClass, NgFor, forwardRef(() => MenuItemComponent), SharedModule],
})
export class MenuItemComponent extends BaseComponent {
    @Input() item: CustomMenuItem;
    @Input() index: number;

    @Input() root: boolean;

    activated: boolean = false;

    readonly authService: AuthService = inject(AuthService);

    itemClick($event: any) {
        this.activated = !this.activated;
    }

    icon(): IconLookup {
        if (!!this.item.icon) {
            const icon = this.item.icon.split(' ');
            return { prefix: icon[0], iconName: icon[1] } as IconLookup;
        }

        return null;
    }
}

export interface CustomMenuItem extends MenuItem {
    items?: CustomMenuItem[];
    rolesAllowed?: keyof typeof Role | Role | (keyof typeof Role)[] | Role[];
    permissionsAllowed?: Permission | Permission[];
}
