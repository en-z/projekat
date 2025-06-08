import { Component, OnInit } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { FormsModule } from '@angular/forms';
import { CommonModule} from '@angular/common';
import { UniverzitetService } from '../../services/univerzitet.service';
import { AuthService } from '../../services/auth.service';
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'app-univerzitet',
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './univerzitet.component.html',
  styleUrl: './univerzitet.component.css'
})
export class UniverzitetComponent implements OnInit{
  univerziteti:Univerzitet[] = [];
  constructor(private univerzitetService:UniverzitetService,public authService:AuthService,public router:Router){}
  ngOnInit(): void {
    this.univerzitetService.getAll().subscribe(data=>this.univerziteti = data)
  }
  deleteUniverzitet(id:number){
    if(confirm("Ok")){
      this.univerzitetService.delete(id).subscribe(()=>{
       this.univerziteti = this.univerziteti.filter(u=>u.id !==id)
      })
    }
  }
}
