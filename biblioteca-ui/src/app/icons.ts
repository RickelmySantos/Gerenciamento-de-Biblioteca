import { InjectionToken } from '@angular/core';
import { CoreIcons, CoreIconsType } from './core/util/styles/core.icons';

type AppIconsType = {
    readonly core: CoreIconsType;
};

export const AppIcons: AppIconsType = {
    core: CoreIcons,
};

export const APP_ICONS = new InjectionToken<AppIconsType>('appIcons');
