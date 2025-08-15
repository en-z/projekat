import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { NotifikacijeComponent } from '../notifikacije/notifikacije.component';
@Component({
  standalone:true,
  selector: 'app-nav-bar',
  imports: [CommonModule ,NotifikacijeComponent],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
  template: `
    <app-notifikacije></app-notifkiacije>
  `
})


export class NavBarComponent {
  showStudentmenu = false;
  showNastavnik= false;
  showBiblioteka= false;
  showAdmin= false;
  showOsobljeMenu= false;

  private hideTimeout:any;
  private clearHideTimeout(){
    if(this.hideTimeout){
      clearTimeout(this.hideTimeout);
      this.hideTimeout=null;
    }

  }
  constructor(private router:Router,public authService:AuthService){
  }
  dodajZavrsni(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/zavrsni-rad'])
  }
  goToStudenti(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/studenti'])
  }
  goToNastavniciPrikaz(){
    this.clearHideTimeout();
    this.showAdmin=false;
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
    this.clearHideTimeout();
    this.showStudentmenu=false;
    this.router.navigate(['/prijavi-ispit'])
  }
  goToStudentPredmet(){
    this.clearHideTimeout();
    this.showStudentmenu=false;
    this.router.navigate(['/student/predmet'])
  }
  gotoIstorijaIspita(){
    this.clearHideTimeout();
    this.showStudentmenu=false;
    this.router.navigate(['/ishod-ispita'])
  }
  goToDodajFaks(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/fakultet'])
  }
  goToDodajUni(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/univerzitet'])
  }
  goToDodajPredmet(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/predmet'])
  }
  goToDodajProgram(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/program'])
  }
  goToDodajOsoblje(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/dodaj-osoblje'])

  }
  goToDodajRok(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/dodaj-rok'])
  }
  goToDodajPow(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/add-pow'])
  }
  goToDodajNastavnik(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/nastavnik'])
  }
  goToSifarnik(){
    this.clearHideTimeout();
    this.showAdmin=false;
    this.router.navigate(['/sifarnik'])
  }
  goToExport(){
    this.clearHideTimeout();
    this.showAdmin=false;
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
    this.clearHideTimeout();
    this.showBiblioteka=false;
    this.router.navigate(['/biblioteka'])
  }
  vrati(){
    this.clearHideTimeout();
    this.showBiblioteka=false;
    this.router.navigate(['/biblioteka/iznajmljene'])
  }
  onMenuEnter(){
    this.clearHideTimeout();
  }
  onMenuLeave(){
    this.hideTimeout=setTimeout(()=>{
      this.showBiblioteka=false;
      this.showAdmin=false;
      this.showStudentmenu=false;
  },50);
  }
  goToZavrsniRadovi() {
    this.router.navigate(['/zavrsni-radovi'])
  }
  goToDodajDokumenta() {
    this.router.navigate(['/osoblje/dokumenti'])
  }
  goToDodajRaspored() {
    this.router.navigate(['osoblje/raspored'])
  }
  goToDodajKolokvijum(){
    this.router.navigate(['osoblje/kolokvijum'])
  }
  goToInventar(){
    this.router.navigate(['osoblje/inventar'])
  }
}
