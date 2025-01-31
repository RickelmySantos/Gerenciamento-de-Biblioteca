import { IconUtils } from 'src/app/core/util/styles/icon-util';
import { AppIcons } from 'src/app/icons';
import { CustomMenuItem } from 'src/app/layout/components/menu-item/menu-item.component';

export const MENU: CustomMenuItem[] = [
    {
        label: 'menu.home',
        icon: IconUtils.convertToString(AppIcons.home.module),
        routerLink: ['/'],
    },
];
