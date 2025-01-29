import { KeycloakService } from 'keycloak-angular';

export function initialize(keycloak: KeycloakService) {
    return () =>
        keycloak
            .init({
                config: {
                    clientId: 'angular-ui',
                    realm: 'biblioteca',
                    url: 'http://localhost:8280',
                },
                initOptions: {
                    onLoad: 'login-required',
                    checkLoginIframe: false,
                },
                enableBearerInterceptor: true,
                bearerPrefix: 'Bearer',
                bearerExcludedUrls: ['/assets', '/public'],
            })
            .then(async () => {
                const token = await keycloak.getToken();
                if (token) {
                    localStorage.setItem('access_token', token);
                }
                tokenUpdate(keycloak);
            });
}

function tokenUpdate(keycloak: KeycloakService) {
    const refreshInterval = 60000;

    setInterval(async () => {
        if (await keycloak.isLoggedIn()) {
            keycloak
                .updateToken(30)
                .then(async refreshed => {
                    if (refreshed) {
                        const updatedToken = await keycloak.getToken();
                        localStorage.setItem('access_token', updatedToken || '');
                    }
                })
                .catch(error => {
                    keycloak.login();
                });
        }
    }, refreshInterval);
}
