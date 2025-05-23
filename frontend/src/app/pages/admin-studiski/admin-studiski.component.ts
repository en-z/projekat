import { Component } from '@angular/core';
import { Nastavnik } from '../../models/nastavnik';
import { NastavnikService } from '../../services/nastavink.service';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { StudiskiService } from '../../services/studiski.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-studiski',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-studiski.component.html',
  styleUrl: './admin-studiski.component.css'
})
export class AdminStudiskiComponent {
  listaNastavnika:Nastavnik[]=[]
  listaStudiskih:StudiskiProgram[]=[]
  selectedStudiski:StudiskiProgram|null=null;
  selectedNastavnik:Nastavnik|null=null;
  error:string|null=null;
  loading = false;
  constructor(private studiskiService:StudiskiService,private nastavnikService:NastavnikService){}
  ngOnInit(){
    this.studiskiService.getAll().subscribe({
      next:(data:StudiskiProgram[])=>{
        this.listaStudiskih = data;
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
  putStudiski(studiski:StudiskiProgram,){
    if(!this.selectedNastavnik){
      this.error = "nastavnik nije izabran"
      return
    }
    studiski.rukovodioc = this.selectedNastavnik;
    this.studiskiService.update(studiski.id!,studiski).subscribe({
        next:(res)=>{
          //rout
          this.selectedStudiski = null;
        },
        error:(err:any)=>{
          this.error = err.message ||"error na put"
          console.error(err);
        }
      })
  }
}
