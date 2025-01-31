import { HttpHandlerFn, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { inject } from '@angular/core';
import { OAuthStorage } from 'angular-oauth2-oidc';

import { environment } from 'src/environments/environment';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<any>, next: HttpHandlerFn) => {
    console.debug(`[AuthInterceptor] Interceptada a requisição [${req.method}] ${req.url} `);

    const allowedUrls: string[] = [environment.apiUrl as string];
    const authStorage: OAuthStorage = inject(OAuthStorage);

    const url = req.url.toLowerCase();
    if (!allowedUrls.find(u => url.startsWith(u))) {
        console.debug('`[AuthInterceptor]  Não foi necessário incluir cabeçalho Bearer na requisição');
        return next(req);
    }

    const token = authStorage.getItem('access_token');
    console.debug(`[AuthInterceptor] Token encontrado: ${token ? '[Token Presente]' : '[Token Ausente]'}`);

    if (token) {
        const header = 'Bearer ' + token;
        const headers = req.headers.set('Authorization', header);

        console.debug(`[AuthInterceptor] Cabeçalho 'Bearer [token]' incluído na requisição`, headers);

        req = req.clone({ headers });
    } else {
        console.debug(`[AuthInterceptor] Cabeçalho 'Bearer [token]' NÃO incluído na requisição`);
    }

    return next(req);
};
