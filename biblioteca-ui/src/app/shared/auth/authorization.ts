import { Permission } from 'src/app/shared/auth/permission.enum';
import { READ_SCOPE, USUARIO_ALL_SCOPE, WHITE_SCOPE } from 'src/app/shared/auth/scopes';

export const AUTHORIZATION: { [roles: string]: Permission[] } = {
    DEFAULT: [...READ_SCOPE],
    ADMIN: [...READ_SCOPE, ...WHITE_SCOPE, ...USUARIO_ALL_SCOPE],
    USUARIO: [...READ_SCOPE],
};
