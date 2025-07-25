import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles = route.data['roles'] as string[];
    if (!this.auth.isLoggedIn() || !expectedRoles.some(r => this.auth.hasRole(r))) {
      this.router.navigate(['/unauthorized']);
      return false;
    }
    return true;
  }
}

