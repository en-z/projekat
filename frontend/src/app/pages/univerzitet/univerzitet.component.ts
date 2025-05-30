import { Component, OnInit } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { FormsModule } from '@angular/forms';
import { CommonModule} from '@angular/common';
import { FakultetDTO } from '../../models/fakultet';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Predmet } from '../../models/predmet';
import { UniverzitetService } from '../../services/univerzitet.service';
import { FakultetService } from '../../services/fakultet.service';
import { StudiskiService } from '../../services/studiski.service';
import { PredmetService } from '../../services/predmet.service';
import { AuthService } from '../../services/auth.service';
import { RouterModule } from '@angular/router';
import { SilabusService } from '../../services/silabus.service';
import { Silabus } from '../../models/silabus';
@Component({
  selector: 'app-univerzitet',
  imports: [FormsModule,CommonModule,RouterModule],
  templateUrl: './univerzitet.component.html',
  styleUrl: './univerzitet.component.css'
})
export class UniverzitetComponent implements OnInit{
  univerziteti:Univerzitet[] = [];
  fakulteti:FakultetDTO[]=[];
  programi:StudiskiProgram[]=[];
  predmeti:Predmet[]=[]
  univerzitetId?:number;
  fakultetId?:number;
  programId?:number;
  predmetId?:number;
  silabusi:Silabus[]=[];// ovo treba da je lista mozda
  constructor(private silabusService:SilabusService,private univerzitetService:UniverzitetService,private fakultetService:FakultetService,private programService:StudiskiService,private predmetService:PredmetService,public authService:AuthService){}
  ngOnInit(): void {
    this.univerzitetService.getAll().subscribe(data=>this.univerziteti = data)
  }
  onUniverzitetChange(id:number){
    this.univerzitetId = id;
    this.fakultetService.getByUniverzitetId(id).subscribe(data=>{
      this.fakulteti = data;
      this.programi = []
      this.predmeti = []
    })
  }
  onFakultetChange(id: number) {
    this.fakultetId= id;
    this.programService.getByFakultet(id).subscribe(data => {
      this.programi= data;
      this.predmeti= [];
    });
  }
  onProgramChange(id: number) {
    this.programId= id;
    this.predmetService.getByProgram(id).subscribe(data => {
      this.predmeti= data;
    });
  }
  onSilabusChange(id:number){
    this.predmetId = id;
    this.silabusService.getAllByPredmetId(id).subscribe((data)=>{
      this.silabusi= data;
    })
  }
  deleteUniverzitet(id: number) {
    this.univerzitetService.delete(id).subscribe(() => {
      this.univerziteti = this.univerziteti.filter(u => u.id !== id);
    });
  }

  deleteFakultet(id: number) {
    this.fakultetService.delete(id).subscribe(() => {
      this.fakulteti = this.fakulteti.filter(f => f.id !== id);
    });
  }

  deleteProgram(id: number) {
    this.programService.delete(id).subscribe(() => {
      this.programi = this.programi.filter(p => p.id !== id);
    });
  }

  deletePredmet(id: number) {
    this.predmetService.delete(id).subscribe(() => {
      this.predmeti = this.predmeti.filter(pr => pr.id !== id);
    });
  }

}
