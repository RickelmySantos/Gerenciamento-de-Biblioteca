import { HttpClient, provideHttpClient } from '@angular/common/http';
import { bootstrapApplication, BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from 'src/app/app.component';

import { FaIconLibrary } from '@fortawesome/angular-fontawesome';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fas } from '@fortawesome/free-solid-svg-icons';

import { APP_INITIALIZER, importProvidersFrom } from '@angular/core';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { provideOAuthClient } from 'angular-oauth2-oidc';
import { AuthService } from 'src/app/core/auth/auth.service';
import { httpLoaderFactory } from 'src/app/core/translate/translate-loader-factory';
import { APP_ICONS, AppIcons } from 'src/app/icons';
import { APP_STYLES, AppStyles } from 'src/app/style';

bootstrapApplication(AppComponent, {
    providers: [
        provideHttpClient(),
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
    ],
}).catch(e => console.error(e));
