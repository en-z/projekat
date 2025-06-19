import { Component, OnInit } from '@angular/core';
import { StudiskiService } from '../../services/studiski.service';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Predmet } from '../../models/predmet';
import { Silabus } from '../../models/silabus';
import { PredmetService } from '../../services/predmet.service';
import { SilabusService } from '../../services/silabus.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { NastavniMaterijal } from '../../models/NastavniMaterijal';

@Component({
  selector: 'app-prikazi-program',
  imports: [CommonModule],
  templateUrl: './prikazi-program.component.html',
  styleUrl: './prikazi-program.component.css'
})
export class PrikaziProgramComponent implements OnInit {
  program:StudiskiProgram|null = null
  predmeti:Predmet[]=[]
  silabusi:Silabus[]=[]
  nastavniMaterijali:NastavniMaterijal[]=[]
  id:number|null = null;
  idPredmet:number|null = null;
  constructor(private progrmaService:StudiskiService,
             private predmetService:PredmetService,
             private silabusService:SilabusService,
             public router:Router,
             private route:ActivatedRoute,
             private http:HttpClient){

             }
  ngOnInit():void{
    this.id =Number(this.route.snapshot.paramMap.get('id'))
    this.progrmaService.getById(this.id!).subscribe(data=>{this.program = data
      this.loadPredmete();
    })
  }
  loadPredmete(){
    this.predmetService.getByProgram(this.id!).subscribe(data=>{
      this.predmeti = data
      console.log(data)
    })
  }
  loadSilabuse(idPredmet:number){
    console.log(this.predmeti);
    this.idPredmet = idPredmet;
    this.silabusi = []
    this.silabusService.getAllByPredmetId(idPredmet).subscribe(data=>this.silabusi = data)
    this.loadNastavniMaterijal();
  }
  loadNastavniMaterijal(){
    this.http.get<any[]>(`http://localhost:8080/api/nastavnik/materijali/predmet/${this.idPredmet}`).subscribe(data=>this.nastavniMaterijali= data);
  }
}
