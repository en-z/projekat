import { Routes } from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { AdminDodajStudiskiComponent } from './pages/admin-dodaj-studiski/admin-dodaj-studiski.component';
import { AdminDodajFakultetComponent } from './pages/admin-dodaj-fakultet/admin-dodaj-fakultet.component';
import { AdminDodjaUniverziteComponent } from './pages/admin-dodja-univerzite/admin-dodja-univerzite.component';
import { UniverzitetComponent } from './pages/univerzitet/univerzitet.component';
import { NavBarComponent } from './pages/nav-bar/nav-bar.component';
import { NastavnikPredmetiComponent } from './pages/nastavnik/predmeti/nastavnik-predmeti/nastavnik-predmeti.component';
import { PredmetDetaljiComponent } from './pages/nastavnik/predmeti/predmet-detalji/predmet-detalji.component';
import { SilabusComponent } from './pages/nastavnik/silabus/silabus.component';
import { TerminiComponent } from './pages/nastavnik/termini/termini.component';
import { EvaluacijeComponent } from './pages/nastavnik/evaluacije/evaluacije.component';
import { ObavestenjaComponent } from './pages/nastavnik/obavestenja/obavestenja/obavestenja.component';
import { StudentiPredmetaComponent } from './pages/nastavnik/studenti-predmeta/studenti-predmeta.component';
import { PretragaStudenataComponent } from './pages/nastavnik/pretraga-studenata/pretraga-studenata/pretraga-studenata.component';
import { StudentProfilComponent } from './pages/nastavnik/student-profil/student-profil/student-profil.component';
import { UnosOcenaComponent } from './pages/nastavnik/unos-ocena/unos-ocena.component';
import { EditProfileComponent } from './pages/edit-profile/edit-profile.component';
import { AdminDodajIspitnirokComponent } from './pages/admin-dodaj-ispitnirok/admin-dodaj-ispitnirok.component';
import { PrijaviIspitComponent } from './pages/prijavi-ispit/prijavi-ispit.component';
import { IshodIspitaComponent } from './pages/ishod-ispita/ishod-ispita.component';
import { AdminDodajPredmetComponent } from './pages/admin-dodaj-predmet/admin-dodaj-predmet.component';
export const routes: Routes = [
  {
    path:"login",component:LoginComponent
  },
  {
    path:"register",component:RegistrationComponent
  },
  {
    path:"fakultet/:id",component:AdminDodajFakultetComponent
  },
  {
   path:"univerzitet/:id",component:AdminDodjaUniverziteComponent
  },
  {
  path:"program/:id",component:AdminDodajStudiskiComponent
  },
  {
    path:"univerziteti",component:UniverzitetComponent
  },
  {
    path:"",component:NavBarComponent
  },
  {
    path:"univerzitet",component:AdminDodjaUniverziteComponent
  },
  {
    path:"fakultet",component:AdminDodajFakultetComponent
  },
  {
    path:"program",component:AdminDodajStudiskiComponent
  },
  {
    path:"predmet",component:AdminDodajPredmetComponent
  },
  {
    path:"predmet/:id",component:AdminDodajPredmetComponent
  },
  {
    path:"edit",component:EditProfileComponent
  },
  {
    path:"ispitni-rok",component:AdminDodajIspitnirokComponent
  },
  {
    path:"prijavi-ispit",component:PrijaviIspitComponent
  },
  {
    path:"ishod-ispita",component:IshodIspitaComponent
  },
   { path: 'nastavnik/predmeti', component: NastavnikPredmetiComponent },
  { path: 'nastavnik/predmet/:id', component: PredmetDetaljiComponent },
  { path: 'nastavnik/predmet/:id/silabus', component: SilabusComponent },
  { path: 'nastavnik/predmet/:id/termini', component: TerminiComponent },
  { path: 'nastavnik/predmet/:id/evaluacije', component: EvaluacijeComponent },
  { path: 'nastavnik/predmet/:id/obavestenja', component: ObavestenjaComponent },
  { path: 'nastavnik/predmet/:id/studenti', component: StudentiPredmetaComponent },
  { path: 'nastavnik/pretraga-studenata', component: PretragaStudenataComponent },
  { path: 'nastavnik/student/:id', component: StudentProfilComponent },
  { path: 'nastavnik/predmet/:id/ocene', component: UnosOcenaComponent },

  { path: '**', redirectTo: 'login' }
];
