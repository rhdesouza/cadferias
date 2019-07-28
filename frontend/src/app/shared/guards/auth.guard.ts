import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanLoad, Route, UrlSegment } from '@angular/router';
import { Observable } from "rxjs";

import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate, CanLoad {

  constructor(
    private authService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.authService.loggedIn$;
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean> {
    return this.authService.loggedIn$;
  }
}
