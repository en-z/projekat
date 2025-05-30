import { Component,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Predmet } from '../../models/predmet';
import { PrijavaService } from '../../services/prijava.service';
import { PrijavaIspit } from '../../models/prijavaIspit';
import { HttpClient } from '@angular/common/http';
import { IspitniRokService } from '../../services/ispitniRok.service';
import { IspitniRok } from '../../models/ispitniRok';
@Component({
  selector: 'app-prijavi-ispit',
  imports: [CommonModule],
  templateUrl: './prijavi-ispit.component.html',
  styleUrl: './prijavi-ispit.component.css'
})

export class PrijaviIspitComponent implements OnInit{
  listaPredmeta:Predmet[]=[];
  listaRokova:IspitniRok[] = [];
  error:string|null = null;
  loading = true;
  selectedPredmet:number|null= null;
  selectedRokId:number|null = null;
  constructor(private rokService:IspitniRokService,private prijava:PrijavaService,private http:HttpClient){}
  ngOnInit(): void {
    this.rokService.getAktivne().subscribe({
      next:(data)=>{
        if(data.length === 0){
          this.error = 'Nema aktivnih ispitnih rokova'
          this.loading = false;
        }else{
          this.listaRokova= data;
          this.getPredmete();
        }
      }
    })
  }
  getPredmete(){
    this.prijava.getAll().subscribe({
      next: (data) => {
        this.listaPredmeta = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = err.message || 'Greška pri učitavanju predmeta.';
        this.loading = false;
      }
    });
  }
  selected(id:number):void{
    this.selectedPredmet = id;
  }
  onSubmit(){
    if(this.selectedPredmet && this.selectedRokId){
      let date = new Date();
      let selectedPrijava:PrijavaIspit={
          id:null,
          godina: date.getFullYear(),
          rok:this.selectedRokId,
          datumPrijave: date,
          predmetId:this.selectedPredmet
      }
      this.http.post(``,selectedPrijava).subscribe();
    }
  }
}
