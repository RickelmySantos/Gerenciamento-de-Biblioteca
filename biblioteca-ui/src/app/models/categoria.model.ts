import { IconName, IconPrefix } from '@fortawesome/fontawesome-svg-core';
import { Entity } from 'src/app/core/models/entity.model';

export interface Categoria extends Entity<number> {
    id: number;
    nome: string;
    cor: string;
    icon?: () => [IconPrefix, IconName];
}
export function mapCategoria(entity: Categoria): Categoria {
    if (!entity) return null;
    return {
        ...entity,
        icon: () => ICON_MAP[entity.nome] || ['fas', 'question'],
    } as Categoria;
}

const ICON_MAP: { [key: string]: [IconPrefix, IconName] } = {
    TECNOLOGIA: ['fas', 'chalkboard-user'],
    DBA: ['fas', 'gavel'],
    DESGINER: ['fas', 'globe'],
    IA: ['fas', 'microchip'],
    AUTOMAÇÃO: ['fas', 'robot'],
};
