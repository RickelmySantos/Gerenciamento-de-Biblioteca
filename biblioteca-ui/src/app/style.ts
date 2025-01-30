import { InjectionToken } from '@angular/core';
import { CoreStyleType, CoreSyle } from 'src/app/core/util/styles/core.styles';

type StyleType = {
    readonly core: CoreStyleType;
};

export const AppStyles: StyleType = {
    core: CoreSyle,
};

export const APP_STYLES = new InjectionToken<StyleType>('appStyles');
