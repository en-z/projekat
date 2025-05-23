import { Component } from '@angular/core';
import { Nastavnik } from '../../models/nastavnik';
import { FakultetDTO } from '../../models/fakultet';
import { NastavnikService } from '../../services/nastavink.service';
import { FakultetService } from '../../services/fakultet.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-fakultet',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-fakultet.component.html',
  styleUrl: './admin-fakultet.component.css'
})
export class AdminFakultetComponent {
  listaNastavnika:Nastavnik[]=[]
  listaFakulteta:FakultetDTO[]=[]
  selectedUniverzitet:FakultetDTO|null=null;
  selectedNastavnik:Nastavnik|null=null;
  error:string|null=null;
  loading = false;
  constructor(private fakultetService:FakultetService,private nastavnikService:NastavnikService){}
  ngOnInit(){
    this.fakultetService.getAll().subscribe({
      next:(data:FakultetDTO[])=>{
        this.listaFakulteta = data;
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
  putStudiski(fakultet:FakultetDTO,){
    if(!this.selectedNastavnik){
      this.error = "nastavnik nije izabran"
      return
    }
    fakultet.dekan= this.selectedNastavnik;
    this.fakultetService.update(fakultet.id!,fakultet).subscribe({
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
