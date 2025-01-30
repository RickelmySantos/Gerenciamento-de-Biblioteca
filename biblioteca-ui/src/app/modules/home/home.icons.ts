import { IconProp } from '@fortawesome/fontawesome-svg-core';
import { BaseModuleIconsType } from 'src/app/core/util/base-module.types';
import { IconUtils } from 'src/app/core/util/styles/icon-util';

export type HomeIconsType = {
    readonly alertaMensagemUsuario: IconProp;
} & BaseModuleIconsType;

export const HomeIcons: HomeIconsType = {
    module: IconUtils.iconProp('fas', 'house'),
    alertaMensagemUsuario: IconUtils.iconProp('fas', 'bell'),
};
