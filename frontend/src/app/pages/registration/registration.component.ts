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
      email: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      passwordConf: ['', [Validators.required, Validators.minLength(5), this.passwordConfirmValidator.bind(this)]],
      ime: ['', [Validators.required]],
      prezime: ['', [Validators.required]],
      ulica: ['', [Validators.required]],
      broj: ['', [Validators.required]],
      grad: ['', [Validators.required]],
      drzava: ['', [Validators.required]],
      roles:['ROLE_ADMIN']
    });
  }

  passwordConfirmValidator(control: any) {
    if (control.value !== this.registrationForm?.get('password')?.value) {
      return { mismatch: true };
    }
    return null;
  }

  get f() {return this.registrationForm.controls}

  onSubmit(){
    if(this.registrationForm.invalid) return;
    this.submitted = true;
    const valute = this.registrationForm.value;
    const lowValute= Object.keys(valute).reduce((acc: { [key: string]: any }, key: string) => {
      if(key==="password"|| key ==="passwordConf"){
        acc[key] = valute[key]
      }else{
        acc[key] = typeof valute[key] === 'string' ? valute[key].toLowerCase() : valute[key];
      }
    return acc;
  }, {});
    this.http.post("http://localhost:8080/api/auth/registration",lowValute).subscribe({
      next:(res)=>{
        //route na login home
    },error:(e)=>{
      if(e.status === 409 && e.error){
        this.error = e.error;
      }else{
        this.error = "Greska na serveru"
      }
      this.submitted = false;
    },
    complete:()=>{this.submitted = false}
    });
  }
}

