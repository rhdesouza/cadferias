import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { environment } from '../../environments/environment';

import { StateVO } from '../shared/model/vo/address/state-vo';
import { CityVO } from '../shared/model/vo/address/city-vo';
import { BaseService } from '../shared/common/base-service';
import { Employee } from '../shared/model/employee';
import { EmployeeFilterVO } from '../shared/model/vo/filter/employee-filter-vo';
import { SearchBySelectVO } from '../shared/model/vo/search-by-select-vo';
import { SortVO, Direction } from '../shared/model/vo/pagination/sort-vo';

const EMPLOYEES='employees';
@Injectable({
  providedIn: 'root'
})
export class EmployeesService extends BaseService<Employee, EmployeeFilterVO> {

  constructor(
    protected http: HttpClient
  ) { 
    super(http, `${environment.API}${EMPLOYEES}`);
  }

  get allStates$() {
    return this.http.get<StateVO[]>('assets/data/brazilian-states.json');
  }

  get allCities$() {
    return this.http.get<CityVO[]>('assets/data/brazilian-cities.json');  
  }

  getByIdWithoutPhotos$(id: number) {        
    return this.http.get<Employee>(`${this.API_URL}/withoutPhotos/${id}`);
  }

  getByName$(name: string) {
    const search = <SearchBySelectVO>{};
    search.searchValue = name;
    search.sort = <SortVO>{property: 'name', direction: Direction.ASC};
    search.size = this.searchSize;
    return this.http.post<Employee[]>(`${this.API_URL}/searchByName`, search);
  }
}
