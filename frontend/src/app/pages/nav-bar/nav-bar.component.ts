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
  showDodaj=false;
  showPrikaz=false;
  showOsobljeMenu= false;
  showAdmin=false;

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
    this.showDodaj=false;
    this.router.navigate(['/zavrsni-rad'])
  }
  goToStudenti(){
    this.clearHideTimeout();
    this.showPrikaz=false;
    this.router.navigate(['/studenti'])
  }
  goToNastavniciPrikaz(){
    this.clearHideTimeout();
    this.showPrikaz=false;
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
  goToPrikaz(){
    this.router.navigate(['univerzitet/prikazi-univerzitet'])
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
    this.showDodaj=false;
    this.router.navigate(['/fakultet'])
  }
  goToDodajUni(){
    this.clearHideTimeout();
    this.showDodaj=false;
    this.router.navigate(['/univerzitet'])
  }
  goToDodajPredmet(){
    this.clearHideTimeout();
    this.showDodaj=false;
    this.router.navigate(['/predmet'])
  }
  goToDodajProgram(){
    this.clearHideTimeout();
    this.showDodaj=false;
    this.router.navigate(['/program'])
  }
  goToDodajOsoblje(){
    this.clearHideTimeout();
    this.showDodaj=false;
    this.router.navigate(['/dodaj-osoblje'])

  }
  goToDodajRok(){
    this.clearHideTimeout();
    this.showDodaj=false;
    this.router.navigate(['/dodaj-rok'])
  }
  goToDodajPow(){
    this.clearHideTimeout();
    this.showDodaj=false;
    this.router.navigate(['/add-pow'])
  }
  goToDodajNastavnik(){
    this.clearHideTimeout();
    this.showDodaj=false;
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
  zahtjevi(){
    this.clearHideTimeout();
    this.showBiblioteka = false;
    this.router.navigate(['/biblioteka/izdate'])
  }
  onMenuEnter(){
    this.clearHideTimeout();
  }
  onMenuLeave(){
    this.hideTimeout=setTimeout(()=>{
      this.showBiblioteka=false;
      this.showAdmin=false;
      this.showDodaj=false;
      this.showPrikaz=false;
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
  goToDodajOpsta(){
    this.router.navigate(['osoblje/opsta-d'])
  }
  goToOpsta(){
    this.router.navigate(['osoblje/opsta'])
  }
  goToPregledKolokvijuma(){
    this.router.navigate(['osoblje/pregled-kolokvijuma'])
  }
  goToSlusanja(){
    this.router.navigate(['osoblje/dodaj-slusanja'])
  }
  goToPrikaziDokumente(){
    this.router.navigate(['prikazi-dokumente'])
  }
}
