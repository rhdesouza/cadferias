import { Component, AfterViewInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BaseDetails } from 'src/app/shared/common/base-details';
import { getRel } from 'src/app/shared/model/base-entity';
import { Employee, EMPLOYEE_ENDPOINT_GET_BY_SEARCH_REL, EMPLOYEE_ENDPOINT_GET_BY_ID_REL, EMPLOYEE_ENDPOINT_DELETE_REL, getPhoto, EMPLOYEE_PHOTO } from 'src/app/shared/model/employee';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.scss']
})
export class EmployeeDetailsComponent extends BaseDetails<Employee> implements AfterViewInit {

  @ViewChild('imagePreview', { read: ElementRef, static:true }) imagePreview: ElementRef;
   
  constructor(
    private renderer: Renderer2,
    protected route: ActivatedRoute,
    protected router: Router
  ) { 
    super(route, router);
  }

  ngAfterViewInit() {
    const photo = getPhoto(super.entity);
    this.renderer.setStyle(this.imagePreview.nativeElement, 'background-image', `url(${!!photo ? photo : EMPLOYEE_PHOTO})`);
  }

  get getBySearchRel() {
    return getRel(super.entity._links, EMPLOYEE_ENDPOINT_GET_BY_SEARCH_REL);
  }

  get getByIdRel() {
    return getRel(super.entity._links, EMPLOYEE_ENDPOINT_GET_BY_ID_REL);
  }
  
  get deleteRel() {
    return getRel(super.entity._links, EMPLOYEE_ENDPOINT_DELETE_REL);
  }

}
