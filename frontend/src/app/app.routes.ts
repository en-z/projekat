import { Routes } from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { NastavnikPredmetiComponent } from './pages/nastavnik/predmeti/nastavnik-predmeti/nastavnik-predmeti.component';
import { PredmetDetaljiComponent } from './pages/nastavnik/predmeti/predmet-detalji/predmet-detalji.component';
import { SilabusComponent } from './pages/nastavnik/silabus/silabus.component';
import { TerminiComponent } from './pages/nastavnik/termini/termini.component';
import { EvaluacijeComponent } from './pages/nastavnik/evaluacije/evaluacije.component';
import { ObavestenjaComponent } from './pages/nastavnik/obavestenja/obavestenja/obavestenja.component';
import { StudentProfilComponent } from './pages/nastavnik/student-profil/student-profil/student-profil.component';
import { PretragaStudenataComponent } from './pages/nastavnik/pretraga-studenata/pretraga-studenata/pretraga-studenata.component';
import { UnosOcenaComponent } from './pages/nastavnik/unos-ocena/unos-ocena.component';
import { StudentiPredmetaComponent } from './pages/nastavnik/studenti-predmeta/studenti-predmeta.component';

export const routes: Routes = [
  { path:"login",component:LoginComponent },
  { path:"register",component:RegistrationComponent },

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
