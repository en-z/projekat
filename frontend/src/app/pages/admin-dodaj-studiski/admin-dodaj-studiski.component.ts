import { Component } from '@angular/core';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Nastavnik } from '../../models/nastavnik';
import { FakultetDTO } from '../../models/fakultet';
import { NastavnikService } from '../../services/nastavink.service';
import { FakultetService } from '../../services/fakultet.service';
import { StudiskiService } from '../../services/studiski.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-admin-dodaj-studiski',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-dodaj-studiski.component.html',
  styleUrl: './admin-dodaj-studiski.component.css'
})
export class AdminDodajStudiskiComponent {
  nastavnikLista:Nastavnik[]=[]
  fakultetLista:FakultetDTO[]=[]
  selectedNastavnik:Nastavnik|null=null;
  selectedFakultet:FakultetDTO|null=null;

  error:string|null = null;

  noviStudiski:Partial<StudiskiProgram> = {
    id:null,
    naziv:"",
    opis:"",
  }

  constructor(private nastavnikService:NastavnikService,private fakultetService:FakultetService,private studiskiService:StudiskiService){}

  ngOnInit(){
    this.nastavnikService.getAll().subscribe({
      next:(data:Nastavnik[])=>{
        this.nastavnikLista =data;
      },
      error:(err:any)=>{
        this.error =err.message ||"nastavnik service puko"
        console.log(err)
      }
    });
    this.fakultetService.getAll().subscribe({
      next:(data:FakultetDTO[])=>{
        this.fakultetLista =data;
      },error:(err:any)=>{
        this.error =err.message ||"fakultet service puko"
        console.log(err)
      }
    })
  }
  onSubmit(){
    this.error = null;
    if(!this.selectedNastavnik || !this.selectedFakultet ){
        this.error = "nastavnik ili fakultet nije izbran"
        return
    }
    if(!this.noviStudiski.naziv || !this.noviStudiski.opis){
      return
    }
    const payload :StudiskiProgram = {
      id: null,
      naziv: this.noviStudiski.naziv ,
      opis: this.noviStudiski.opis ,
      rukovodioc: this.selectedNastavnik,
      fakultet: this.selectedFakultet,
    };
    this.studiskiService.create(payload).subscribe({
    next: () => {
      this.noviStudiski = { naziv: '', opis: '' };
      this.selectedNastavnik = null;
      this.selectedFakultet = null;
      console.log("Studiski program dodat!");
    },
    error: (err: any) => {
      this.error = err.message || "Greska kod dodavanja programa";
      console.error(err);
    },
  });
  }
}
