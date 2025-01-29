import { User } from 'src/app/core/model/user.model';
import { AUTHORIZATION } from 'src/app/shared/auth/authorization';
import { Permission } from 'src/app/shared/auth/permission.enum';
import { Role } from 'src/app/shared/auth/role.enum';

export function hasRole(user: User, roles: keyof typeof Role | Role | (keyof typeof Role)[] | Role[]): boolean {
    if (!user) {
        return false;
    }

    if (Array.isArray(roles)) {
        return roles?.some(role => user.roles.includes(role));
    }

    return user.roles.includes(roles);
}

export function hasPermission(user: User, permission: Permission | Permission[]): boolean {
    if (!user) {
        return false;
    }

    if (permission === null || permission === undefined) {
        return true;
    }

    if (Array.isArray(permission)) {
        if (!permission.length) {
            return true;
        }
        return permission?.some(p => checkPermission(user, p));
    }
    return checkPermission(user, permission);
}

export function checkPermission(user: User, permission: Permission): boolean {
    if (user.roles?.length) {
        for (let role of user.roles) {
            if (AUTHORIZATION[role].includes(permission)) {
                return true;
            } else {
                if (AUTHORIZATION['DEFAULT'].includes(permission)) {
                    return true;
                }
            }
        }
    }
    return false;
}
