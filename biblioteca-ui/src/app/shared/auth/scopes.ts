import { Permission } from 'src/app/shared/auth/permission.enum';

export const READ_SCOPE: Permission[] = [Permission.VISUALIZAR];

export const WHITE_SCOPE: Permission[] = [Permission.CONFIGURACAO_ALTERAR_SEGURANCA];

export const USUARIO_WHITE_SCOPE: Permission[] = [Permission.USUARIO_INCLUIR, Permission.USUARIO_ALTERAR, Permission.USUARIO_CADASTRAR, Permission.USUARIO_FAVORITAR, Permission.USUARIO_REMOVER];
export const USUARIO_READ_SCOPE: Permission[] = [Permission.USUARIO_VISUALIZAR];
export const USUARIO_ADMIN_SCOPE: Permission[] = [Permission.USUARIO_REMOVER];
export const USUARIO_ALL_SCOPE: Permission[] = [...USUARIO_READ_SCOPE, ...USUARIO_WHITE_SCOPE, ...USUARIO_ADMIN_SCOPE];
