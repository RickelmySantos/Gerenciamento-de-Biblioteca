import { inject, Injectable, InjectionToken } from '@angular/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { Observable } from 'rxjs';
import { hasPermission, hasRole } from 'src/app/core/auth/aut-util';
import { authConfig } from 'src/app/core/auth/auth-config';
import { User } from 'src/app/core/modules/seguranca/model/user.model';
import { UniqueDataStore } from 'src/app/core/store/unique-data.store';
import { Permission } from 'src/app/shared/auth/permission.enum';
import { Role } from 'src/app/shared/auth/role.enum';

export const AUTH_STORE = new InjectionToken<UniqueDataStore<User>>('AuthStore', {
    providedIn: 'root',
    factory: () => new UniqueDataStore<User>(),
});

@Injectable()
export class AuthService {
    private userProfile: User;

    private readonly oauthService: OAuthService = inject(OAuthService);

    private readonly userStore: UniqueDataStore<User> = inject(AUTH_STORE);

    constructor() {
        this.oauthService.configure(authConfig);
        this.oauthService.setupAutomaticSilentRefresh();

        this.oauthService.disablePKCE = false;
    }

    get user$(): Observable<User> {
        return this.userStore.value$;
    }

    get user(): User {
        return this.userStore.value;
    }

    async initAuth(): Promise<void> {
        try {
            await this.oauthService.loadDiscoveryDocument();

            await this.oauthService.tryLoginCodeFlow();

            if (this.hasValidToken()) {
                console.log('Usuário já autenticado, carregando aplicação...');
                return;
            }
            this.login();
        } catch (error) {
            console.error('Erro ao inicializar autenticação:', error);
            this.login();
        }
    }

    async loadTokens(): Promise<boolean> {
        try {
            await this.oauthService.loadDiscoveryDocument();
            await this.oauthService.tryLoginCodeFlow();
            return this.hasValidToken();
        } catch (error) {
            console.error('Erro ao carregar tokens:', error);
            return false;
        }
    }

    login(): void {
        this.oauthService.initLoginFlow();
    }

    logout(): void {
        this.oauthService.revokeTokenAndLogout();
        this.clearUserProfile();
    }

    private hasValidToken(): boolean {
        return this.oauthService.hasValidAccessToken();
    }

    authenticatedUserHasRoles(roles: keyof typeof Role | Role | (keyof typeof Role)[] | Role[]): boolean {
        return this.user ? hasRole(this.user, roles) : false;
    }

    authenticatedUserHasPermissions(permissions: Permission | Permission[]): boolean {
        return this.user ? hasPermission(this.user, permissions) : false;
    }

    private clearUserProfile(): void {
        this.userStore.value = null;
    }
}
