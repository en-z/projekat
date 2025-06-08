import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IshodService } from '../../services/ishod.service';
import { IshodIspita } from '../../models/ishodIspita';
import { FormsModule} from '@angular/forms';
@Component({
  selector: 'app-ishod-ispita',
  imports: [CommonModule,FormsModule],
  templateUrl: './ishod-ispita.component.html',
  styleUrl: './ishod-ispita.component.css'
})
export class IshodIspitaComponent {
  ishodLista:IshodIspita[]=[];
  polozeniIspiti:(IshodIspita&{ocena:number})[]=[];
  pretragaIspita:IshodIspita[]=[];
  error:string|null = null;
  loading=true
  totalEsbp:number=0
  selectedFilter: 'ocena' | 'naziv' | 'pokusaj' = 'ocena';
  constructor(private ishod:IshodService){}

  ngOnInit(): void {
    this.ishod.getAll().subscribe({
      next:(data)=>{
        this.ishodLista= data,
          this.grp();
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
      return Math.floor((n-1)/10)+1; //dobijanje ocjene
    }
    grp():void{
      for(var p of this.ishodLista){
        if(p.bodovi>=51){
          const ocena = this.getOcena(p.bodovi)
          this.polozeniIspiti.push({...p,ocena})
          this.totalEsbp += p.predmet.espb
        }
      }
    }
    searchByOcena(ocena:number){
      this.pretragaIspita=[]
      if(ocena <=5){
        alert("lista padnutih ispita");
        return;
      }
      const minBodovi = 51;
      const interval = 10;
      const lowVal=minBodovi + (ocena-6)*interval;
      const upVal= lowVal + interval -1;
      this.pretragaIspita = this.ishodLista.filter(i=>i.bodovi>=lowVal && i.bodovi <=upVal);
    }
    seachByNaziv(term:String){
      this.pretragaIspita=[]
      const trimed = term.trim().toLowerCase()
      this.pretragaIspita = this.ishodLista.filter(i=>i.predmet.naziv.toLowerCase().includes(trimed))
    }
    searchByPokusaj(pokusaj:number){
      this.pretragaIspita =[]
      this.pretragaIspita = this.ishodLista.filter(i=>i.brojPokusaja === pokusaj)
    }

    onFilterInput(event: Event) {
      const target = event.target as HTMLInputElement;
      const value = target.value;
      if (!value.trim()) {
        this.pretragaIspita = [];
        return;
      }

      switch (this.selectedFilter) {
        case 'ocena':
          const ocenaNum = Number(value);
        if (!isNaN(ocenaNum)) {
          this.searchByOcena(ocenaNum);
        }
        break;

        case 'naziv':
          this.seachByNaziv(value);
        break;

        case 'pokusaj':
          const pokusajNum = Number(value);
        if (!isNaN(pokusajNum)) {
          this.searchByPokusaj(pokusajNum);
        }
        break;
      }
}

}
