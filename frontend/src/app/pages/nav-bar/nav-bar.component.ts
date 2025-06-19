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
  showBiblioteka= false;
  showAdmin= false;
  constructor(private router:Router,public authService:AuthService){
  }
  dodajZavrsni(){
    this.router.navigate(['/zavrsniRad'])
  }
  goToStudenti(){
    this.router.navigate(['/studenti'])
  }
  goToNastavniciPrikaz(){
    this.router.navigate(['/nastavnici'])
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
  goToEdit(){
    this.router.navigate(['/edit'])
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
  goToDodajNastavnik(){
    this.router.navigate(['/nastavnik'])
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
  goToNastavnikPredmeti(){
    this.router.navigate(['/nastavnik/predmeti'])
  }
  goToBiblioteka(){
    this.router.navigate(['/biblioteka'])
  }
  vrati(){
    this.router.navigate(['/biblioteka/iznajmljene'])
  }
}
