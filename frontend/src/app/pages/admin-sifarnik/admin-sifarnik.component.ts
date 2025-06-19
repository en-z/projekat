import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user';
import { FormsModule } from '@angular/forms';
import { error } from 'console';
@Component({
  selector: 'app-admin-sifarnik',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-sifarnik.component.html',
  styleUrl: './admin-sifarnik.component.css'
})
export class AdminSifarnikComponent {
  genpw:string|null=null;
  email:string='';
  error:string|null= null;
  loading:boolean = true;
  userList:User[]=[];
  userSelected:User|null=null;
  constructor(private userService:UserService){}
  getAll(){
    this.userService.getAll().subscribe({
      next:(data:User[])=>{
        this.userList = data
        this.loading = false;
    },
    error:(err:any)=>{
      this.error =err.message ||"error na fetch all"
      this.loading = false;
    }
    });
  }
  getByEmail(){
    if(!this.email){
      this.error ="potreban email"
      return;
    }
    this.userService.getByEmail(this.email).subscribe({
      next:(user:User)=>{
        this.userList = [user]
        this.loading = false;
      },
      error:err=>this.error = err.message
    })
  }
  genPassword():string{
    let password='';
    for(let i= 0;i<8;i++) {
      const charCode = Math.floor(Math.random()*(126-33*1))+33 // en:mozda samo ascii brojevi i slova bez znakova?
      password += String.fromCharCode(charCode)
    }
    return password;
  }

  onSubmit(user:User){
    this.userSelected = user;
    let password = this.genPassword()
    this.userSelected.password = password;
    this.userService.update(this.userSelected.id,this.userSelected).subscribe({
      next:(data)=>{
        this.genpw= password;
      },error:(err:any)=>{
        this.error = err.message || "error na put";
      }
    })
  }
}
