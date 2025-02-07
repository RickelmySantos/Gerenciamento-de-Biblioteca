(function (window) {
    window['env'] = window['env'] || {};

    // Environment variables
    window['env']['API_URL'] = '${API_URL}';
    window['env']['BASE_URL'] = '${BASE_URL}';

    window['env']['DEFAULT_TIMEZONE'] = '${DEFAULT_TIMEZONE}';
    window['env']['DEFAULT_LOCALE'] = '${DEFAULT_LOCALE';

    window['env']['OAUTH_ISSUER'] = '${OAUTH_ISSUER}';
    window['env']['OAUTH_CLIENT_ID'] = '${OAUTH_CLIENT_ID}';

    window['env']['REQUIRE_HTTPS'] = '${REQUIRE_HTTPS}';
})(this);
