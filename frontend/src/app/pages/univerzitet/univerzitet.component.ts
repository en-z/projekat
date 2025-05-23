import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Univerzitet } from '../../models/univerzitet';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NgFor ,NgIf } from '@angular/common';
import { FakultetDTO } from '../../models/fakultet';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Predmet } from '../../models/predmet';
@Component({
  selector: 'app-univerzitet',
  imports: [FormsModule,NgFor,NgIf],
  templateUrl: './univerzitet.component.html',
  styleUrl: './univerzitet.component.css'
})
export class UniverzitetComponent implements OnInit{
  lUniverziteti:Univerzitet[]=[]
  lFakulteti:FakultetDTO[]=[]
  lStudiski:StudiskiProgram[]=[]
  lPredmet:Predmet[]=[]
  selectedNumber:number|null = null;
  selectedProgram:number|null = null; //!!! OVO JE ODJE SAMO READI TESITRANJA MORA SE IZVUC
  selectedPredmet:number|null = null;
  constructor(private http:HttpClient ,private router:Router){ // router mozda nece trebat
  }
  ngOnInit(){
    this.http.get<Univerzitet[]>(`http://localhost:8080/univerzitet`).subscribe(data=>{
      this.lUniverziteti= data;
    })
  }
  onListChange(){
    if(this.selectedNumber!== null){
      this.http.get<FakultetDTO[]>(`http://localhost:8080/univerzitet/${this.selectedNumber}/fakulteti`)
        .subscribe(data=>{this.lFakulteti = data});
    }if(this.selectedProgram!==null){ // ovo je sve test (DELETE)
      this.http.get<StudiskiProgram[]>(`http://localhost:8080/univerzitet/fakultet/${this.selectedProgram}/program`).subscribe(data=>{this.lStudiski = data});
        console.log(this.lStudiski);
    }if(this.selectedPredmet !==null){
      this.http.get<Predmet[]>(`http://localhost:8080/univerzitet/fakultet/program/${this.selectedProgram}/predmet`).subscribe(data=>{this.lPredmet = data});
        console.log(this.lPredmet);
    }

  }
}
