export const authProperties = {
    issuer: window['env']['OAUTH_ISSUER'] !== '${OAUTH_ISSUER}' ? window['env']['OAUTH_ISSUER'] : 'http://localhost:8280/auth/realms/REALM_LOCAL',
    clientId: window['env']['OAUTH_CLIENT_ID'] !== '${OAUTH_CLIENT_ID}' ? window['env']['OAUTH_CLIENT_ID'] : 'biblioteca-ui',
    redirectUri: window.location.origin,
    responseType: 'code',
    strictDiscoveryDocumentValidation: true,
    scope: 'openid profile email roles offline_access',
    requireHttps: window['env']['REQUIRED_HTTPS'] === 'true',
    showDebugInformation: true,
};
