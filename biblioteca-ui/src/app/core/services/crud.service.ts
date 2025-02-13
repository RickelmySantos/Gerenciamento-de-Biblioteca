import { Injectable } from '@angular/core';
import { Entity } from 'src/app/core/models/entity.model';
import { QueryService } from 'src/app/core/services/query.service';

@Injectable()
export abstract class CrudService<E extends Entity<any>> extends QueryService<E> {
    protected override getURl(): string {
        return `${this.API}/${this.PATH}`;
    }
}
