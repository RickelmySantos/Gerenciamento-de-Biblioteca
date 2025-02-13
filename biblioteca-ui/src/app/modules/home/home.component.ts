import { ChangeDetectionStrategy, Component } from '@angular/core';

import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-home',
    template: `
        <section
            class="h-14rem flex flex-column justify-content-center align-items-start surface-section surface-border border-bottom-1 px-4 pt-4 pb-0"
        >
            test
        </section>
    `,

    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule],
})
export class HomeComponent extends BaseComponent {}
