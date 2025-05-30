import { Component, OnInit } from '@angular/core';
import { Form, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import 'jwt-decode'
import { jwtDecode } from 'jwt-decode';
import { NastavnikService } from '../../services/nastavink.service';
import { OsobaService } from '../../services/osoba.service';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

interface JwtPayload {
  roles:string[]|string
}
@Component({
  selector: 'app-edit-profile',
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css'
})
export class EditProfileComponent implements OnInit {
  userType:'user'|'nastavnik'= 'user';
  profilForm!:FormGroup;
  constructor(private http:HttpClient,private userService:OsobaService,private fb:FormBuilder,private nastavnikService:NastavnikService){}
  ngOnInit(): void {
    const token = localStorage.getItem('jwtToken')||'';
    this.getRoleFromToken(token);
    this.forma();
    if(this.userType == 'nastavnik'){
      this.nastavnikService.getNoIdFromToken().subscribe((data)=>{
        this.profilForm.patchValue(data)
      })
    }else{
      this.userService.getNoIdFromToken().subscribe((data)=>{
        this.profilForm.patchValue(data)
      })
    }
  }
  getRoleFromToken(token:string){
    const decoded = jwtDecode<JwtPayload>(token);
    let roles = decoded.roles;
    if(typeof roles ==='string')roles = [roles];
    if(roles.includes('ROLE_TEACHER')) this.userType = 'nastavnik';
    else this.userType = this.userType = 'user';
  }
  forma(){
    const base = this.fb.group({
      email:[''],
      password:[''],
      ime:[''],
      prezime:[''],
      adresa:this.fb.group({
        ulica:[''],
        broj:[''],
        grad:[''],
        drzava:[''],
      }),
    });
    if(this.userType =='nastavnik'){
      (base as FormGroup).addControl('status',this.fb.control(''));
      (base as FormGroup).addControl('biografija',this.fb.control(''));
    }
    this.profilForm = base;
  }
  onSubmit(){
    if(this.profilForm.valid){
      this.http.post('http://localhost:8080/api/editprofile',this.profilForm.value);
    }
  }
}
