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
          if(p.bodovi <=51){//valjda je manje polozenih ispita nego palih ispita
            this.padIspit.push(p);
          }else{
            this.polozeniIspiti.push(p);
          }
      }
    }
}
