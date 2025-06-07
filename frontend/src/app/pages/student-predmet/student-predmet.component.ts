import { Component } from '@angular/core';
import { Predmet } from '../../models/predmet';
import { Obavjestenja } from '../../models/obavjestenja';
import { PredmetService } from '../../services/predmet.service';
import { CommonModule } from '@angular/common';
import { ObavestenjeService } from '../../services/obavestenje.service';
import { Obavestenje } from '../../models/obavestenje';

@Component({
  selector: 'app-student-predmet',
  imports: [CommonModule],
  templateUrl: './student-predmet.component.html',
  styleUrl: './student-predmet.component.css'
})
export class StudentPredmetComponent {
  predmetList:Predmet[]= [];
  selectedPredmet:Predmet|null = null;
  obavjestenja:Obavestenje[]=[]
  error:string|null=null;
  constructor(private obavjestenjaService:ObavestenjeService,private predmetService:PredmetService){}

  ngOnInit(){
    this.predmetService.getPredmeteZaSlusanje().subscribe({
      next:(data:Predmet[])=>{
        this.predmetList = data;
      },
      error:(err:any)=>{
        this.error = err.message || "error na fetch predmete";
      }
    })
  }
  getObavjestenja(predmet:Predmet){
    this.obavjestenja = []
    this.obavjestenjaService.getByPredmet(predmet.id!).subscribe(data=>this.obavjestenja = data)
  }
}
