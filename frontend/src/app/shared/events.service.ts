import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { EventVO } from './model/vo/event-vo';
import { MessageType } from './model/vo/message/message-vo';
import { AlertMessageVO } from './model/vo/message/alert-message-vo';
import { AuditableFilterVO, FilterType } from './model/vo/filter/auditable-filter-vo';
import { EmployeeFilterVO } from './model/vo/filter/employee-filter-vo';
import { VacationFilterVO } from './model/vo/filter/vacation-filter-vo';
import { StaffFilterVO } from './model/vo/filter/staff-filter-vo';

@Injectable({
  providedIn: 'root'
})
export class EventsService {

  private filterSubject = new Map<FilterType, BehaviorSubject<any>>();
  private eventSubject = new Subject<EventVO>();
  private alertSubject = new Subject<AlertMessageVO>();

  constructor() { 
    this.initSearchSubject();
  }

  private initSearchSubject() {
    Object.keys(FilterType).filter(key => isNaN(Number(key))).map(key => FilterType[key]).forEach(searchType => {
      this.filterSubject.set(searchType, new BehaviorSubject<any>(undefined));  
    });
  }

  addEmployeeFilter(employee?: EmployeeFilterVO) {
    this.addFilter(FilterType.EMPLOYEE, employee);
  }

  addVacationFilter(vacation?: VacationFilterVO) {
    this.addFilter(FilterType.VACATION, vacation);
  }

  addStaffFilter(staff?: StaffFilterVO) {
    this.addFilter(FilterType.STAFF, staff);
  }

  private addFilter(searchType: FilterType, filter: AuditableFilterVO) {
    this.filterSubject.get(searchType).next(filter);
  }

  getFilter$(filterType: FilterType) {
    return this.filterSubject.get(filterType).asObservable();
  }

  resetAllSearchs() {
    this.addEmployeeFilter();
    this.addVacationFilter();
    this.addStaffFilter();
  }

  endAllEvents() {
    Array.from(this.filterSubject.keys()).forEach(searchType => 
      this.filterSubject.get(searchType).complete()
    );
    this.filterSubject.clear();
    this.eventSubject.complete();
    this.alertSubject.complete();
  }
  
  addEvent(event: EventVO) {
    this.eventSubject.next(event);
  }

  get events$() {
    return this.eventSubject.asObservable();
  }

  addSuccessAlert(title: string, message: string, timeout?: number) {
    this.addAlert(MessageType.SUCCESS, title, message, timeout);
  }

  addInfoAlert(title: string, message: string, timeout?: number) {
    this.addAlert(MessageType.INFO, title, message, timeout);
  }

  addWarningAlert(title: string, message: string, timeout?: number) {
    this.addAlert(MessageType.WARNING, title, message, timeout);
  }

  addDangerAlert(title: string, message: string, timeout?: number) {
    this.addAlert(MessageType.DANGER, title, message, timeout);
  }

  private addAlert(type: MessageType, title: string, message: string, timeout?: number) {
    const alert = <AlertMessageVO> {
      type: type,
      title: title,
      message: message,
      timeout: (timeout ? timeout : 10000)
    };
    this.alertSubject.next(alert);
  }

  get alert$() {
    return this.alertSubject.asObservable();
  }
}