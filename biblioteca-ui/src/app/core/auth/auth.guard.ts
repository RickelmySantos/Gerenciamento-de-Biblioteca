import { inject, Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
    protected readonly authService: KeycloakService = inject(KeycloakService);
    protected readonly router: Router = inject(Router);

    async canActivate(): Promise<boolean> {
        const isLoggedIn = await this.authService.isLoggedIn();
        if (!isLoggedIn) {
            console.debug('[AuthGuard] Usuário não autenticado!');
            await this.authService.login();
            return false;
        }
        console.debug('[AuthGuard] Usuário autenticado!');
        return true;
    }
}
