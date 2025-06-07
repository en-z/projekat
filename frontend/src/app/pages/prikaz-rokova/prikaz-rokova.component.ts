import { Component, OnInit } from '@angular/core';
import { IspitniRokService } from '../../services/ispitniRok.service';
import { IspitniRok } from '../../models/ispitniRok';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-prikaz-rokova',
  imports: [CommonModule],
  templateUrl: './prikaz-rokova.component.html',
  styleUrl: './prikaz-rokova.component.css'
})
export class PrikazRokovaComponent implements OnInit{
  rokovi:IspitniRok[]=[]
  constructor(private router:Router,private rokService:IspitniRokService,public authServie:AuthService){}
  ngOnInit() {
    this.rokService.getAktivne().subscribe(data=>this.rokovi = data);
  }
  navToPut(id:number){
    this.router.navigate([`/dodaj-rok/${id}`])
  }
  navToAdd(){
    this.router.navigate(['/dodaj-rok'])
  }
}
