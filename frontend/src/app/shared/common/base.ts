import { OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { filter } from 'rxjs/operators';

export abstract class Base implements OnDestroy { 
    
    private endSubject = new Subject<boolean>();

    protected isNumber(value: string | number) {
        return (value && !isNaN(Number(value)));
    }

    protected isNumberOrNull(value: string | number) {
        return !value || this.isNumber(value);
    }

    protected getNumber(value: string | number) {
        if(!value)
            return null;
        
        return Number(value);
    }

    protected buildMap(value: any, key?: string): Map<string, any> {
        if(!value)
            return new Map<string, any>();

        return Object.keys(value).reduce((map, k) => {
            const v = value[k];

            if(typeof v === 'function')
                return map;

            if(typeof v === 'object')
                return new Map([...Array.from(map.entries()), ...Array.from(this.buildMap(v, k).entries())])
            
            return map.set(!!key ? `${key}.${k}` : k, v);
        }, new Map<string, any>());
    }

    ngOnDestroy() {
        this.endSubject.next(true);
        this.endSubject.complete();
    }

    protected get end$() {
        return this.endSubject.pipe(
            filter(end => end)
        );
    }
}
