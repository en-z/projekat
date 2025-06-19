import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dodaj-nastavnika',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-nastavnika.component.html',
  styleUrl: './dodaj-nastavnika.component.css'
})
export class DodajNastavnikaComponent {
  adminForm: FormGroup;
  error: string | null = null;
  isTeacher = false;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.adminForm=this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      ime: ['', [Validators.required]],
      prezime: ['', [Validators.required]],
      ulica: ['', [Validators.required]],
      broj: ['', [Validators.required]],
      grad: ['', [Validators.required]],
      drzava: ['', [Validators.required]],
      status: ['', [Validators.required]],
      biografija: ['', [Validators.required]],
      roles:[["ROLE_NASTAVNIK"]],
    });
  }
  onSubmit(){
    if(this.adminForm.invalid)return;

    const data =this.adminForm.value;
    this.http.post("http://localhost:8080/api/nastavnik/nastavnici",data).subscribe();
  }

}
