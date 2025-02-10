import { ChangeDetectionStrategy, Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from 'src/app/core/auth/auth.service';
import { BaseComponent } from 'src/app/core/util/base.component';
import { MenuService } from 'src/app/layout/service/menu.service';
import { MENU } from 'src/app/menu';
import defaultLanguage from '../assets/i18n/pt.json';

@Component({
    selector: 'app-root',
    template: `
        <router-outlet></router-outlet>
    `,
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    imports: [RouterOutlet],
})
export class AppComponent extends BaseComponent {
    constructor(private readonly translateService: TranslateService, private readonly authService: AuthService, private readonly menuService: MenuService) {
        super();
        translateService.addLangs(['pt']);
        translateService.setTranslation('pt', defaultLanguage);
        translateService.setDefaultLang('pt');
        this.menuService.load(MENU);

        const browserLang = translateService.getBrowserLang();
        const lang = browserLang.match(/pt/) ? browserLang : 'pt';
        this.changeLang(lang);
    }

    changeLang(lang: string): void {
        this.translateService.use(lang);
    }

    override ngOnInit(): void {
        this.authService.initAuth().then(() => {
            if (!this.authService.hasValidToken()) {
                console.log('Usuário sem token, redirecionando para login...');

                this.authService.login();
            }
        });
    }
}
