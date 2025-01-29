import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { bootstrapApplication, BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { AppComponent } from './app/app.component';
import { ROUTES } from './app/app.routes';
import { httpLoaderFactory } from './app/core/translate/translate-loader-factory';

bootstrapApplication(AppComponent, {
    providers: [
        provideZoneChangeDetection({ eventCoalescing: true }),
        provideRouter(ROUTES),
        provideClientHydration(),
        BrowserAnimationsModule,
        BrowserModule,
        CommonModule,
        importProvidersFrom(
            TranslateModule.forRoot({
                loader: {
                    provide: TranslateLoader,
                    useFactory: httpLoaderFactory,
                    deps: [HttpClient],
                },
            })
        ),
    ],
}).catch(err => console.error(err));
