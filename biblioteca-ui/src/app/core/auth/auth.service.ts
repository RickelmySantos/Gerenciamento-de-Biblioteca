import { HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable, InjectionToken } from '@angular/core';
import { Router } from '@angular/router';
import { LoginOptions, OAuthService } from 'angular-oauth2-oidc';
import { KeycloakService } from 'keycloak-angular';
import { firstValueFrom, Observable } from 'rxjs';
import { hasPermission, hasRole } from 'src/app/core/auth/aut-util';
import { User } from 'src/app/core/model/user.model';
import { UniqueDataStore } from 'src/app/core/store/unique-data.store';
import { Permission } from 'src/app/shared/auth/permission.enum';
import { Role } from 'src/app/shared/auth/role.enum';

export const AUTH_STORE = new InjectionToken<UniqueDataStore<User>>('AuthStore', { providedIn: 'root', factory: () => new UniqueDataStore<User>() });

@Injectable()
export class AuthService {
    router: Router = inject(Router);
    private readonly keycloackService: KeycloakService = inject(KeycloakService);

    private userProfile: User;

    private readonly oauthService: OAuthService = inject(OAuthService);
    private readonly userStore: UniqueDataStore<User> = inject(AUTH_STORE);

    get user(): User {
        return this.userStore.value;
    }

    get user$(): Observable<User> {
        return this.userStore.value$;
    }

    login() {
        this.oauthService.initCodeFlow();
    }

    logout() {
        this.oauthService.revokeTokenAndLogout();
    }

    userIsValid(user: User): boolean {
        return user?.email.trim().length > 0;
    }

    private async loadTokens(): Promise<boolean> {
        return await this.oauthService.loadDiscoveryDocumentAndTryLogin({} as LoginOptions).catch(err => {
            return this.oauthService.loadDiscoveryDocumentAndTryLogin();
        });
    }

    private validToken(): boolean {
        return this.oauthService.hasValidIdToken() && this.oauthService.hasValidAccessToken();
    }

    async userIsAuthenticated(): Promise<boolean> {
        let user: User = await firstValueFrom(this.user$);

        if (this.userIsValid(user)) {
            return true;
        }

        const hasReceivedTokens: boolean = await this.loadTokens();
        if (!hasReceivedTokens) {
            return false;
        }

        user = await this.loadUserProfile();

        return this.userIsValid(user);
    }

    private async loadUserProfile(): Promise<User> {
        if (this.validToken()) {
            this.userProfile = await this.oauthService
                .loadUserProfile()
                .then(userProfile => {
                    const user = { name: userProfile['info']['name'], email: userProfile['info']['email'], login: userProfile['info']['preferred_username'] } as User;
                    return user;
                })
                .catch((err: HttpErrorResponse) => {
                    return null;
                });

            this.userStore.value = this.userProfile;
        }

        return this.userProfile;
    }

    hasRole(roles: keyof typeof Role | Role | (keyof typeof Role)[] | Role[]): boolean {
        return hasRole(this.user, roles);
    }

    hasAuthenticationPermission(permission: Permission | Permission[]): boolean {
        return hasPermission(this.user, permission);
    }

    // hasAnyRole(roles: Role[]): boolean {
    //     return roles.some(role => this.hasRole(role));
    // }
}
