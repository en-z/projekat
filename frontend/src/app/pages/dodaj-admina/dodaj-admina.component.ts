import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators ,ReactiveFormsModule} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule, NgIf } from '@angular/common';
@Component({
  selector: 'app-dodaj-admina',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-admina.component.html',
  styleUrl: './dodaj-admina.component.css'
})
export class DodajAdminaComponent {
  adminForm: FormGroup;
  error: string | null = null;
  isTeacher = false;
  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.adminForm=this.fb.group({
      userType:['admin'],
      email: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(5)]],
      ime: ['', [Validators.required]],
      prezime: ['', [Validators.required]],
      ulica: ['', [Validators.required]],
      broj: ['', [Validators.required]],
      grad: ['', [Validators.required]],
      drzava: ['', [Validators.required]],
      roles:[[]],
    });
  }
  onUserTypeChange(){
    const type = this.adminForm.get('userType')?.value;
    if(type === "teacher"){
      this.isTeacher = true;
      if(!this.adminForm.contains('status')){
        this.adminForm.addControl('status',this.fb.control('',Validators.required));
      }
    }else{
      this.isTeacher = false;
      if(!this.adminForm.contains('status')){
        this.adminForm.removeControl('status')
      }
    }
  }
  onSubmit(){
    if(this.adminForm.invalid)return;

    const data =this.adminForm.value;
    this.http.post("http://localhost:8080/auth/admin/addUser",data).subscribe();
  }
}

