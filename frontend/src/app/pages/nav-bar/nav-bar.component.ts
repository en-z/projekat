import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
@Component({
  standalone:true,
  selector: 'app-nav-bar',
  imports: [CommonModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  showStudentmenu = false;
  showNastavnik= false;
  showAdmin= false;
  constructor(private router:Router,public authService:AuthService){
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
  goToPrijaviIspit(){
    this.router.navigate(['/prijava-ispita'])
  }
  goToStudentPredmet(){
    this.router.navigate(['/student/predmet'])
  }
  gotoIstorijaIspita(){
    this.router.navigate(['/ishod-ispita'])
  }
  goToDodajFaks(){
    this.router.navigate(['/fakultet'])
  }
  goToDodajUni(){
    this.router.navigate(['/univerzitet'])
  }
  goToDodajPredmet(){
    this.router.navigate(['/predmet'])
  }
  goToDodajProgram(){
    this.router.navigate(['/program'])
  }
  goToDodajRok(){
    this.router.navigate(['/dodaj-rok'])
  }
  goToDodajPow(){
    this.router.navigate(['/add-pow'])
  }
  goToSifarnik(){
    this.router.navigate(['/sifarnik'])
  }
  goToExport(){
    this.router.navigate(['/export-user'])
  }
  goToUniverziteti(){
    this.router.navigate(['/univerziteti'])
  }
  goToNastavnici(){
    this.router.navigate(['/nastavnik/predmeti'])
  }

}
