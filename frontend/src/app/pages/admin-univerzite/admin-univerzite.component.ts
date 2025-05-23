import { Component } from '@angular/core';
import { Nastavnik } from '../../models/nastavnik';
import { NastavnikService } from '../../services/nastavink.service';
import { Univerzitet } from '../../models/univerzitet';
import { UniverzitetService } from '../../services/univerzitet.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-admin-univerzite',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-univerzite.component.html',
  styleUrl: './admin-univerzite.component.css'
})
export class AdminUniverziteComponent {
  listaNastavnika:Nastavnik[]=[]
  listaUniverzitet:Univerzitet[]=[]
  selectedUniverzitet:Univerzitet|null=null;
  selectedNastavnik:Nastavnik|null=null;
  error:string|null=null;
  loading = false;
  constructor(private univerzitetService:UniverzitetService,private nastavnikService:NastavnikService){}
  ngOnInit(){
    this.univerzitetService.getAll().subscribe({
      next:(data:Univerzitet[])=>{
        this.listaUniverzitet = data;
        this.loading = false;
      },
      error:(err:any)=>{
        this.error = err.message||"getAll puko"
        this.loading = false;
      }
    })
    this.getNastavnike();
  }
  getNastavnike(){
    this.nastavnikService.getAll().subscribe({
      next:(data:Nastavnik[])=>{
        this.listaNastavnika = data;
      },
      error:(err:any)=>{
        this.error = err.message ||"error na get nastavnike";
      }
    })
  }
  putStudiski(univerzitet:Univerzitet,){
    if(!this.selectedNastavnik){
      this.error = "nastavnik nije izabran"
      return
    }
    univerzitet.rektor= this.selectedNastavnik;
    this.univerzitetService.update(univerzitet.id!,univerzitet).subscribe({
        next:(res)=>{
          //rout
          this.selectedUniverzitet = null;
        },
        error:(err:any)=>{
          this.error = err.message ||"error na put"
          console.error(err);
        }
      })
  }
}

