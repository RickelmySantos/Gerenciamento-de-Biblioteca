export const environment = {
    production: false,
    securityEnabled: true,
    versao: '1.0.0',

    baseUrl: window['env']['BASE_URL'] !== '${BASE_URL}' ? window['env']['BASE_URL'] : 'http://localhost:4200',
    apiUrl: window['env']['API_URL'] !== '${API_URL}' ? window['env']['API_URL'] : 'http://localhost:8080',
    defaultTimezone: window['env']['DEFAULT_TIMEZONE'] !== '${DEFAULT_TIMEZONE}' ? window['env']['DEFAULT_TIMEZONE'] : 'America/Belem',
    defaultLocale: window['env']['DEFAULT_LOCALE'] !== '${DEFAULT_LOCALE}' ? window['env']['DEFAULT_LOCALE'] : 'pt-BR',
};
