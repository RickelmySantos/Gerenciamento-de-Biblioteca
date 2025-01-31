import { ChangeDetectionStrategy, Component } from '@angular/core';
import { BaseComponent } from 'src/app/core/util/base.component';
import { SharedModule } from 'src/app/shared/shared.module';

@Component({
    selector: 'app-footer',
    templateUrl: './footer.component.html',
    styles: [``],
    standalone: true,
    changeDetection: ChangeDetectionStrategy.OnPush,
    imports: [SharedModule],
})
export class FooterComponent extends BaseComponent {}
