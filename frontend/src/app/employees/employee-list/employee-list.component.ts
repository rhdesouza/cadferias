import { Component, Renderer2 } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormControl, FormArray } from '@angular/forms';

import { EventsService, SearchType } from 'src/app/shared/events.service';
import { BaseList } from 'src/app/shared/common/base-list';
import { EmployeesService } from '../employees.service';
import { Employee, Address } from './../../shared/model/employee';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent extends BaseList<Employee> {
        
  constructor(
    protected renderer: Renderer2,
    protected route: ActivatedRoute,
    protected router: Router,
    protected fb: FormBuilder,    
    protected eventsService: EventsService,
    private employeesService: EmployeesService
  ) { 
    super(renderer, route, router, fb, eventsService, employeesService);
  }

  protected addModelSearch(employee: Employee) {
    this.eventsService.addEmployeeSearch(employee);
  }  

  protected getSearchType() {
    return SearchType.EMPLOYEE;
  }

  protected getModelBySearch(search: string): Employee {
    const employee = <Employee>{};  
    //employee.employeeRegistration =search;
    employee.name=search;
    //employee.phoneNumber=search; 
    //employee.address = <Address>{};  
    //employee.address.street = search;
    //employee.address.neighborhood = search;
    //employee.address.city = search;
    //if(this.isNumber(search))
    //    employee.address.houseNumber = Number(search);

    return employee;
  }

  protected getSearchByModel(employee: Employee): string {
    return super.buildMap(employee).get('name');
  }

}
