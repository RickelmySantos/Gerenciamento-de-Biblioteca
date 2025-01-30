export const authProperties = {
    issuer: 'http://localhost:8280/auth/realms/REALM_LOCAL',
    clientId: 'biblioteca-ui',
    redirectUri: window.location.origin,
    responseType: 'code',
    strictDiscoveryDocumentValidation: true,
    scope: 'openid profile email roles offline_access',
    requireHttps: false,
    showDebugInformation: true,
};
