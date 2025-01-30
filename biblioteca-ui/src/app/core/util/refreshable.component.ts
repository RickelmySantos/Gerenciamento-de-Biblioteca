import { AfterViewInit, ChangeDetectorRef, Directive, ElementRef, inject, OnDestroy, OnInit, Renderer2 } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { UUIDUtils } from 'src/app/core/util/uuid-utils';
import { APP_ICONS } from 'src/app/icons';
import { APP_STYLES } from 'src/app/style';

@Directive({ standalone: true })
export class RefreshableComponent implements OnInit, AfterViewInit, OnDestroy {
    protected readonly cd: ChangeDetectorRef = inject(ChangeDetectorRef);
    protected readonly translate: TranslateService = inject(TranslateService);

    public readonly el: ElementRef = inject(ElementRef);
    public readonly renderer: Renderer2 = inject(Renderer2);
    protected readonly uuid: UUIDUtils = new UUIDUtils();

    public readonly icons = inject(APP_ICONS);
    public readonly styles = inject(APP_STYLES);
    ngAfterViewInit(): void {}

    ngOnInit(): void {}

    ngOnDestroy(): void {}

    refresh(): void {
        this.markForRefresh();
        this.cd.detectChanges();
    }

    markForRefresh(): void {
        this.cd.markForCheck();
    }
}
