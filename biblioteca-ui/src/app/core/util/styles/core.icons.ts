import { IconProp } from '@fortawesome/fontawesome-svg-core';
import { IconUtils } from 'src/app/core/util/styles/icon-util';

export type CoreIconsType = {
    readonly pesquisar: IconProp;
    readonly novo: IconProp;
    readonly alterar: IconProp;
    readonly visualizar: IconProp;
    readonly excluir: IconProp;
    readonly excluir_solido: IconProp;
    readonly confirmar: IconProp;
    readonly salvar: IconProp;
    readonly cancelar: IconProp;
    readonly limparFiltros: IconProp;
    readonly fechar: IconProp;
    readonly voltar: IconProp;
    readonly voltar_longo: IconProp;
    readonly login: IconProp;
    readonly logout: IconProp;

    readonly menu: IconProp;

    readonly remover: IconProp;
    readonly favoritar: IconProp;

    readonly autenticado: IconProp;
    readonly usuario: IconProp;
    readonly perfis: IconProp;

    readonly aplicacoes: IconProp;
    readonly logo: IconProp;
    readonly favoritos: IconProp;
};

export const CoreIcons: CoreIconsType = {
    pesquisar: IconUtils.iconProp('fas', 'magnifying-glass'),
    novo: IconUtils.iconProp('fas', 'file-circle-plus'),
    alterar: IconUtils.iconProp('fas', 'pen-to-square'),
    visualizar: IconUtils.iconProp('far', 'eye'),
    excluir: IconUtils.iconProp('far', 'trash-can'),
    excluir_solido: IconUtils.iconProp('fas', 'trash-can'),
    confirmar: IconUtils.iconProp('fas', 'check'),
    salvar: IconUtils.iconProp('fas', 'floppy-disk'),
    cancelar: IconUtils.iconProp('fas', 'xmark'),
    limparFiltros: IconUtils.iconProp('fas', 'times'),
    fechar: IconUtils.iconProp('fas', 'xmark'),
    voltar: IconUtils.iconProp('fas', 'angle-left'),
    voltar_longo: IconUtils.iconProp('fas', 'arrow-left-long'),
    login: IconUtils.iconProp('fas', 'right-to-bracket'),
    logout: IconUtils.iconProp('fas', 'power-off'),

    menu: IconUtils.iconProp('fas', 'bars'),

    remover: IconUtils.iconProp('fas', 'xmark'),
    favoritar: IconUtils.iconProp('fas', 'star'),

    autenticado: IconUtils.iconProp('fas', 'user-lock'),
    usuario: IconUtils.iconProp('far', 'user'),
    perfis: IconUtils.iconProp('fas', 'user-gear'),

    aplicacoes: IconUtils.iconProp('fas', 'sitemap'),
    logo: IconUtils.iconProp('fas', 'image'),
    favoritos: IconUtils.iconProp('far', 'star'),
};
