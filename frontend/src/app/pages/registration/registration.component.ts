import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators ,ReactiveFormsModule} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  imports: [ReactiveFormsModule,NgIf],
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  registrationForm: FormGroup;
  submitted = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required,Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      passwordConf: ['', [Validators.required, Validators.minLength(5), this.passwordConfirmValidator.bind(this)]],
      ime: ['', [Validators.required]],
      prezime: ['', [Validators.required]],
      studiskiId:'1',
      fakultetId:'1'
    });
  }

  passwordConfirmValidator(control: any) {
    if (control.value !== this.registrationForm?.get('password')?.value) {
      return { mismatch: true };
    }
    return null;
  }

  get f() {return this.registrationForm.controls}

success: string | null = null; 

onSubmit() {
  if (this.registrationForm.invalid) return;
  this.submitted = true;
  const valute = this.registrationForm.value;

  const lowValute = Object.keys(valute).reduce((acc: { [key: string]: any }, key: string) => {
    if (key === "password" || key === "passwordConf") {
      acc[key] = valute[key];
    } else {
      acc[key] = typeof valute[key] === 'string' ? valute[key].toLowerCase() : valute[key];
    }
    return acc;
  }, {});

  this.http.post("http://localhost:8080/api/auth/registration", lowValute).subscribe({
    next: (res) => {
      this.success = "Registracija uspešna! Uskoro cete biti preusmereni na pocetnu stranu!"; // ovde poruka uspeha
      this.error = null; // resetuj eventualnu prethodnu grešku

      // automatski preusmeri na početnu posle 2 sekunde
      setTimeout(() => {
        this.router.navigate(['/']);
      },2000);
    },
    error: (e) => {
      if (e.status === 409 && e.error) {
        this.error = e.error;
      } else {
        this.error = "Greška na serveru";
      }
      this.success = null; // nema poruke uspeha
      this.submitted = false;
    },
    complete: () => {
      this.submitted = false;
    }
  });
}


  
}

