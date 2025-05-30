import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-nav-bar',
  imports: [CommonModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  constructor(private router:Router,private authService:AuthService){
  }
  onLogout(){
    this.authService.logout();
  }
  isLogged():boolean{
    return this.authService.isLoggedIn();
  }
  goToLogin(){
    this.router.navigate(['/login'])
  }
  goToRegister(){
    this.router.navigate(['/register'])
  }
}
