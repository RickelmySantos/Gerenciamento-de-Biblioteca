import { CustomMenuItem } from 'src/app/core/layout/components/menu-item/menu-item.component';
import { IconUtils } from 'src/app/core/util/styles/icon-util';
import { AppIcons } from 'src/app/icons';

export const MENU: CustomMenuItem[] = [
    {
        label: 'menu.home',
        icon: IconUtils.convertToString(AppIcons.home.module),
        routerLink: ['/'],
    },

    {
        label: 'menu.marketplace',
        icon: 'fas cart-shopping',
        routerLink: ['/marketplace'],
    },
];
