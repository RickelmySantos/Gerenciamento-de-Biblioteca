import { Directive } from '@angular/core';
import { RefreshableComponent } from 'src/app/core/util/refreshable.component';

@Directive({ standalone: true })
export class BaseComponent extends RefreshableComponent {}
