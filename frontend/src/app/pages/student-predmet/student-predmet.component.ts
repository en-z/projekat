import { Component } from '@angular/core';
import { Predmet } from '../../models/predmet';
import { Obavjestenja } from '../../models/obavjestenja';
import { PredmetService } from '../../services/predmet.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-predmet',
  imports: [CommonModule],
  templateUrl: './student-predmet.component.html',
  styleUrl: './student-predmet.component.css'
})
export class StudentPredmetComponent {
  predmetList:Predmet[]= [];
  selectedPredmet:Predmet|null = null;
  obavjestenja:Obavjestenja[]=[]
  error:string|null=null;
  constructor(private predmetService:PredmetService){}

  ngOnInit(){
    this.predmetService.getAll().subscribe({
      next:(data:Predmet[])=>{
        this.predmetList = data;
      },
      error:(err:any)=>{
        this.error = err.message || "error na fetch predmete";
      }
    })
  }
}
