import { Routes } from '@angular/router';
import {LoginComponent} from './pages/login/login.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { AdminFakultetComponent } from './pages/admin-fakultet/admin-fakultet.component';
import { AdminUniverziteComponent } from './pages/admin-univerzite/admin-univerzite.component';
import { AdminStudiskiComponent } from './pages/admin-studiski/admin-studiski.component';
import { AdminDodajStudiskiComponent } from './pages/admin-dodaj-studiski/admin-dodaj-studiski.component';
import { AdminDodajFakultetComponent } from './pages/admin-dodaj-fakultet/admin-dodaj-fakultet.component';
import { AdminDodjaUniverziteComponent } from './pages/admin-dodja-univerzite/admin-dodja-univerzite.component';
export const routes: Routes = [
  {
    path:"login",component:LoginComponent
  },
  {
    path:"register",component:RegistrationComponent
  },
  {
    path:"admin/fakultet",component:AdminFakultetComponent
  },
  {
    path:"admin/univerzitet",component:AdminUniverziteComponent
  },
  {
    path:"admin/program",component:AdminStudiskiComponent
  },
  {
    path:"admin/fakultet/:id",component:AdminDodajFakultetComponent
  },
  {
    path:"admin/univerzitet/:id",component:AdminDodjaUniverziteComponent
  },
  {
    path:"admin/program/:id",component:AdminDodajStudiskiComponent
  }
];
