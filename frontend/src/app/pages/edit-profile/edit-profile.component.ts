import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import 'jwt-decode'
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { ProfilService } from '../../services/profil.service';

@Component({
  selector: 'app-edit-profile',
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit {
  profilForm!:FormGroup;
  userRoles:string[]=[];
  showExtraFields = false;
  constructor(private profilService:ProfilService,private fb:FormBuilder,private authServidce:AuthService){};
  ngOnInit(){
    this.userRoles = this.authServidce.getAllRoles();
    this.showExtraFields = this.userRoles.includes('ROLE_NASTAVNIK');

    this.profilForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      adresa: this.fb.group({
        ulica: ['', Validators.required],
        grad: ['', Validators.required],
        postanskiBroj: ['', Validators.required],
      }),
      status: [''],
      biografija: [''],
    });

    if (!this.showExtraFields) {
      this.profilForm.get('status')?.disable();
      this.profilForm.get('biografija')?.disable();
    }
    this.loadProfil()
  }
  loadProfil() {
    this.profilService.getProfil().subscribe({
      next: (profil) => {
        this.profilForm.patchValue({
          email: profil.email,
          ime: profil.ime,
          prezime: profil.prezime,
          adresa: profil.adresa,
          status: profil.status,
          biografija: profil.biografija,
        });

        if (this.showExtraFields) {
          this.profilForm.get('status')?.enable();
          this.profilForm.get('biografija')?.enable();
        } else {
          this.profilForm.get('status')?.disable();
          this.profilForm.get('biografija')?.disable();
        }
      },
      error: (err) => {
        console.error('Failed to load profil', err);
      },
    });
  }

  onSubmit() {
    if (this.profilForm.valid) {
      this.profilService.postProfil(this.profilForm.value)
    }else{
      alert("Forma nije ispravna")
    }
  }
}
