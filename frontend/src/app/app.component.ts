import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit,OnDestroy{
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
