import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormBuilder,FormGroup,Validators,ReactiveFormsModule } from '@angular/forms';
import { catchError, throwError } from 'rxjs';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone:true,
  imports: [ReactiveFormsModule,NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
    loginForm:FormGroup;
    subbmited=false;
    error:string|null = null;
    constructor(
      private fb:FormBuilder,
      private http:HttpClient,
      private router:Router
    ){
      this.loginForm = this.fb.group({
        username: ['', [Validators.required]],
        password: ['', [Validators.required, Validators.minLength(5)]]
      });

    }
    get f(){
      return this.loginForm.controls;
    }
    onSubmit(){
      if(this.loginForm.invalid)return;
      this.subbmited = true;
      const{username,password} =this.loginForm.value;
      this.http.post<{token:string}>('http://localhost:8080/api/users/login',{username,password})
      .subscribe({
        next:(res)=>{
          console.log(username,password);
          console.log(res.token);
          localStorage.setItem('jwtToken',res.token);
        },error:(err)=>{
          console.error("Login error:",err);
          this.error = "Pogresan login"
          this.subbmited = false;
        },
        complete:()=>{this.subbmited=false}
      });
    }
}
