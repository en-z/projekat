import { Component } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { FakultetDTO } from '../../models/fakultet';
import { Adresa } from '../../models/adresa';
import { FakultetService } from '../../services/fakultet.service';
import { UniverzitetService } from '../../services/univerzitet.service';
import { NastavnikService } from '../../services/nastavink.service';
import { Nastavnik } from '../../models/nastavnik';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-admin-dodaj-fakultet',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-dodaj-fakultet.component.html',
  styleUrl: './admin-dodaj-fakultet.component.css'
})
export class AdminDodajFakultetComponent {
  nastavnikList:Nastavnik[]=[]
  univerzitetList:Univerzitet[]=[]
  selectedUniverzitet:Univerzitet|null=null;
  selectedNastavnik:Nastavnik|null=null;
  noviFakultet:Partial<FakultetDTO> = {
    id:null,
    naziv:"",
    opis:"",
    kontakt:"",
    adresa:{
      id:null,
      ulica:"",
      broj:"",
      grad:"",
      drzava:"",
    }
  }
  error:string|null=null;
  constructor(private fakultetService:FakultetService,private univerziteService:UniverzitetService,private nastavnikService:NastavnikService){}

  ngOnInit(){
    this.nastavnikService.getAll().subscribe({
      next:(data:Nastavnik[])=>{
        this.nastavnikList = data;
      },
      error:(err:any)=>{
        this.error = err.message ||"error on nastavnik"
      }
    })
    this.univerziteService.getAll().subscribe({
      next:(data:Univerzitet[])=>{
        this.univerzitetList = data;
      },
      error:(err:any)=>{
        this.error = err.message ||"error on nastavnik"
      }
    })
  }
  onSubmit(){
    if(!this.selectedNastavnik || !this.selectedUniverzitet){
      this.error = "nisu izabrani nastavnik ili univerzitet"
      return;
    }
    if(!this.noviFakultet ){
      return
    }
    const adresa = this.noviFakultet.adresa!
    const payload:FakultetDTO={
      id: null,
      naziv: this.noviFakultet.naziv!,
      opis: this.noviFakultet.opis!,
      kontakt: this.noviFakultet.kontakt!,
      adresa: {
        id: null,
        ulica: adresa.ulica!,
        broj: adresa.broj!,
        grad: adresa.grad!,
        drzava: adresa.drzava!,
      },
      dekan: this.selectedNastavnik,
      univerzitet: this.selectedUniverzitet
    }
    this.fakultetService.create(payload).subscribe({
      next:()=>{
        //
        console.log("succ")
      },error:(err)=>{
        this.error = err.mesage ||"greska pri kreiranju"
      }
    });
  }
}
