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
import { RoleGuard } from './role.guard';
import { PrikazRokovaComponent } from './pages/prikaz-rokova/prikaz-rokova.component';
import { DodajAdminaComponent } from './pages/dodaj-admina/dodaj-admina.component';
import { AdminSifarnikComponent } from './pages/admin-sifarnik/admin-sifarnik.component';
import { HomeComponent } from './pages/home/home.component';
import { PrikaziUniverzitetComponent } from './pages/prikazi-univerzitet/prikazi-univerzitet.component';
import { PrikaziProgramComponent } from './pages/prikazi-program/prikazi-program.component';
import { ExportUserComponent } from './pages/export-user/export-user.component';
export const routes: Routes = [
  {
    path:"login",component:LoginComponent
  },
  {
    path:"register",component:RegistrationComponent
  },
  {
    path:"fakultet/:id",component:AdminDodajFakultetComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
   path:"univerzitet/:id",component:AdminDodjaUniverziteComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
  path:"program/:id",component:AdminDodajStudiskiComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
    path:"univerziteti",component:UniverzitetComponent
  },
  {
    path:"",component:HomeComponent
  },
  {
    path:"univerzitet",component:AdminDodjaUniverziteComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']}
  },
  {
    path:"fakultet",component:AdminDodajFakultetComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
    path:"program",component:AdminDodajStudiskiComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
    path:"predmet",component:AdminDodajPredmetComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
    path:"predmet/:id",component:AdminDodajPredmetComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']},
  },
  {
    path:"prikazi-univerzitet/:id",component:PrikaziUniverzitetComponent,
  },
  {
    path:"prikazi-fakultet/:id",component:PrikaziUniverzitetComponent,
  },
  {
    path:"prikazi-program/:id",component:PrikaziProgramComponent
  },
  {
    path:"edit",component:EditProfileComponent
  },
  {
    path:"dodaj-rok",component:AdminDodajIspitnirokComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']}
  },{
    path:"prikaz-rok",component:PrikazRokovaComponent
  },
  {
    path:"prijavi-ispit",component:PrijaviIspitComponent,canActivate:[RoleGuard],data:{roles:['ROLE_STUDENT']}
  },
  {
    path:"ishod-ispita",component:IshodIspitaComponent,canActivate:[RoleGuard],data:{roles:['ROLE_STUDENT']}
  },
  {
    path:"add-pow",component:DodajAdminaComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']}
  },{
    path:"sifarnik",component:AdminSifarnikComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']}
  },{
    path:"export-user",component:ExportUserComponent,canActivate:[RoleGuard],data:{roles:['ROLE_ADMIN']}
  },
  {
    path:"student/predmet",component:StudentiPredmetaComponent,canActivate:[RoleGuard],data:{roles:['ROLE_STUDENT']}
  },
  { path: 'nastavnik/predmeti', component: NastavnikPredmetiComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/predmet/:id', component: PredmetDetaljiComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/predmet/:id/silabus', component: SilabusComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/predmet/:id/termini', component: TerminiComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/predmet/:id/evaluacije', component: EvaluacijeComponent ,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK']}},
  { path: 'nastavnik/predmet/:id/obavestenja', component: ObavestenjaComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/predmet/:id/studenti', component: StudentiPredmetaComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/pretraga-studenata', component: PretragaStudenataComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/student/:id', component: StudentProfilComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},
  { path: 'nastavnik/predmet/:id/ocene', component: UnosOcenaComponent,canActivate:[RoleGuard],data:{roles:['ROLE_NASTAVNIK'] }},

  { path: '**', redirectTo: 'login' }
];
