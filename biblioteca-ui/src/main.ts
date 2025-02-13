import { HttpClient, provideHttpClient, withInterceptors } from '@angular/common/http';
import { bootstrapApplication, BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from 'src/app/app.component';

import ptBr from '@angular/common/locales/pt';
import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fas } from '@fortawesome/free-solid-svg-icons';

import { DATE_PIPE_DEFAULT_OPTIONS, registerLocaleData } from '@angular/common';
import { APP_INITIALIZER, DEFAULT_CURRENCY_CODE, importProvidersFrom, LOCALE_ID } from '@angular/core';
import { provideRouter } from '@angular/router';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { provideOAuthClient } from 'angular-oauth2-oidc';
import { authInterceptor } from 'src/app/core/auth/auth.interceptor';
import { AuthService } from 'src/app/core/auth/auth.service';
import { httpLoaderFactory } from 'src/app/core/translate/translate-loader-factory';
import { APP_ICONS, AppIcons } from 'src/app/icons';
import { ROUTES } from 'src/app/routes';
import { APP_STYLES, AppStyles } from 'src/app/style';
import { environment } from 'src/environments/environment';

registerLocaleData(ptBr);

bootstrapApplication(AppComponent, {
    providers: [
        provideHttpClient(withInterceptors([authInterceptor])),
        provideOAuthClient(),
        importProvidersFrom(
            BrowserModule,
            BrowserAnimationsModule,
            TranslateModule.forRoot({
                loader: {
                    provide: TranslateLoader,
                    useFactory: httpLoaderFactory,
                    deps: [HttpClient],
                },
            })
        ),
        {
            provide: APP_INITIALIZER,
            useFactory: (library: FaIconLibrary) => {
                library.addIconPacks(fas);
                library.addIconPacks(far);
            },
            deps: [FaIconLibrary],
        },
        {
            provide: AuthService,
            useFactory: () => {
                const service = new AuthService();
                return service;
            },
        },
        { provide: APP_ICONS, useFactory: () => AppIcons },
        { provide: APP_STYLES, useFactory: () => AppStyles },
        { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL' },
        { provide: LOCALE_ID, useValue: environment.defaultLocale },
        { provide: DATE_PIPE_DEFAULT_OPTIONS, useValue: { timezone: environment.defaultTimezone, dateFormat: 'short' } },

        provideRouter(ROUTES),
    ],
}).catch(e => console.error(e));
