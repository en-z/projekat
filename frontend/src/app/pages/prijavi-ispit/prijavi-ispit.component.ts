import { Component,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Predmet } from '../../models/predmet';
import { PrijavaService } from '../../services/prijava.service';
import { PrijavaIspit } from '../../models/prijavaIspit';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-prijavi-ispit',
  imports: [CommonModule],
  templateUrl: './prijavi-ispit.component.html',
  styleUrl: './prijavi-ispit.component.css'
})

export class PrijaviIspitComponent implements OnInit{
  listaPredmeta:Predmet[]=[];
  error:string|null = null;
  loading = true;
  selectedPredmet:number|null= null;
  constructor(private prijava:PrijavaService,private http:HttpClient){}
  ngOnInit(): void {
    this.prijava.getAll().subscribe({
      next:(data)=>{
        this.listaPredmeta = data,
          this.loading = false
      }
      ,error:(err)=>{
        this.error = err.message || 'error na ucitavanje ';
        this.loading =false;
      }
    });
    }
    selected(id:number):void{
      this.selectedPredmet = id;
    }
  onSubmit(){
    if(this.selectedPredmet){
      let date = new Date();
      let selectedPrijava:PrijavaIspit={
          id:null,
          godina: date.getFullYear(),
          rok: date.getMonth(),
          datumPrijave: date,
          predmetId:this.selectedPredmet
      }
      this.http.post(``,selectedPrijava).subscribe();
    }
  }
}
