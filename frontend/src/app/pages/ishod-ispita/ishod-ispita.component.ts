import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IshodService } from '../../services/ishod.service';
import { IshodIspita } from '../../models/ishodIspita';
@Component({
  selector: 'app-ishod-ispita',
  imports: [CommonModule],
  templateUrl: './ishod-ispita.component.html',
  styleUrl: './ishod-ispita.component.css'
})
export class IshodIspitaComponent {
  ishodLista:IshodIspita[]=[];
  polozeniIspiti:IshodIspita[]=[];
  padIspit:IshodIspita[]=[];
  error:string|null = null;
  loading = true;
  constructor(private ishod:IshodService){}

  ngOnInit(): void {
    this.ishod.getAll().subscribe({
      next:(data)=>{
        this.ishodLista= data,
          this.grp();//mozda moze bez kreiranja 2 liste
          this.loading = false
      }
      ,error:(err)=>{
        this.error = err.message || 'error na ucitavanje ';
        this.loading =false;
      }
    });
    }
    getOcena(n:number):number{
      if(n<51 )return 5;
      if(n>100)return 10;
      return Math.floor((n-1)/10); //dobijanje ocjene
    }
    grp():void{
      for(var p of this.ishodLista){
          if(p.bodovi <=51){
            this.padIspit.push(p);
          }else{
            this.polozeniIspiti.push(p);
          }
      }
    }
    getEsbp():number{
      var broj:number=0;
      for(var i of this.polozeniIspiti){
        broj+=i.predmet.espb;
      }
      return broj;
    }
    searchByOcena(ocena:number){
      if(ocena <=5){
        alert("lista padnutih ispita");
        return;
      }
      var bodovi:number = Math.floor((ocena+1)*10)

      const minBodovi = 51;
      const interval = 10;
      const lowVal=minBodovi + (ocena-6)*interval;
      const upVal= lowVal + interval -1;
      return this.polozeniIspiti.filter(i=>i.bodovi>=lowVal && i.bodovi <=upVal);
    }
    searchByIme(query:String){
      return this.ishodLista.filter(i=>i.predmet.naziv.toLowerCase().includes(query.toLowerCase()))
    }
}
