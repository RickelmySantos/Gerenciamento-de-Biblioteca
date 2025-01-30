import { Component, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EmptyPageComponent } from 'src/app/core/components/page-layout/empty-page.component';
import { BaseComponent } from 'src/app/core/util/base.component';

@Component({
    templateUrl: './empty-page-layout.component.html',
    standalone: true,
    imports: [RouterOutlet, EmptyPageComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class EmptyPageLayoutComponent extends BaseComponent {}
