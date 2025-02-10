// export const authGuard: CanMatchFn = async (): Promise<boolean | UrlTree> => {
//     if (!environment.securityEnabled) {
//         console.warn('[AuthGuard] Segurança desabilitada');
//         return firstValueFrom(of(true));
//     }

//     console.debug('[AuthGuard] Segurança habilitada');
//     const authService = inject(AuthService);

//     let userAuthenticated: boolean = await authService.userIsAuthenticated();

//     if (!userAuthenticated) {
//         console.warn('[AuthGuard] O usuário não está autenticado');
//         authService.login();
//     } else {
//         console.debug('[AuthGuard] O usuário está autenticado', authService.user);
//     }

//     return firstValueFrom(of(userAuthenticated));
// };
