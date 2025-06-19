import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';
import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { NotifikacijeComponent } from './pages/notifikacije/notifikacije.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [NavBarComponent, RouterOutlet],
  template: `
    <app-nav-bar></app-nav-bar>
    <router-outlet></router-outlet>
  `
})export class AppComponent implements OnInit,OnDestroy{
  title = 'frontend';
  private tokenCheck:any;
  constructor(private authService:AuthService){}
  ngOnInit(): void {
    this.authService.loadRoleFromJWt();
    this.tokenCheck=setInterval(()=>{
      if(this.authService.isExpired()){
        console.warn("JwtToken istice")
        this.authService.logout();
      }
    },60*1000);
  }
  ngOnDestroy(): void {
    clearInterval(this.tokenCheck);
  }
}
