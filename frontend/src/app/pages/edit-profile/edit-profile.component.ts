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
  showExtraFields = false;
  constructor(private profilService:ProfilService,private fb:FormBuilder,private authServidce:AuthService){};
  ngOnInit(){
    this.profilForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,Validators.minLength(4)]],
    });

    this.loadProfil()
  }
  loadProfil() {
    this.profilService.getProfil().subscribe({
      next: (profil) => {
        this.profilForm.patchValue({
          email: profil.email,
          password: profil.password,
        });
      },
      error: (err) => {
        console.error('Failed to load profil', err);
      },
    });
  }

  onSubmit() {
    if (this.profilForm.valid) {
      this.profilService.postProfil(this.profilForm.value)
      alert("Sacuvano")
    }else{
      alert("Forma nije ispravna")
    }
  }
}
