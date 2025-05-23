import { Component } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { Nastavnik } from '../../models/nastavnik';
import { UniverzitetService } from '../../services/univerzitet.service';
import { NastavnikService } from '../../services/nastavink.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-dodja-univerzite',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-dodja-univerzite.component.html',
  styleUrl: './admin-dodja-univerzite.component.css'
})
export class AdminDodjaUniverziteComponent {
  nastavnikList:Nastavnik[]=[]
  selectedNastavnik:Nastavnik|null = null;
  noviUniverzitet:Partial<Univerzitet> ={
    naziv:"",
    kontakt:"",
    opis:"",
    adresa:{
      id:null,
      ulica:"",
      broj:"",
      grad:"",
      drzava:"",
    },
  }
  error:string | null=null;
  constructor(private univerzitetService:UniverzitetService,private nastavnikService:NastavnikService){}

  ngOnInit(){
    this.nastavnikService.getAll().subscribe({
      next:(data:Nastavnik[])=>{
        this.nastavnikList =data;
      },
      error:(err:any)=>{
        this.error =err.message ||"nastavnik service puko"
        console.log(err)
      }
    });
  }
  onSubmit(){
    if(!this.selectedNastavnik){
      this.error = "nastavnik nije izabran"
      return;
    }
    const adresa =  this.noviUniverzitet.adresa!
    const payload :Univerzitet={
      id:null,
      naziv:this.noviUniverzitet.naziv!,
      opis:this.noviUniverzitet.opis!,
      kontakt:this.noviUniverzitet.kontakt!,
      adresa:adresa,
      rektor:this.selectedNastavnik
    }
    this.univerzitetService.create(payload).subscribe({
      next:()=>{
        this.error ="dodat je univerzitet"
      },error:(err:any)=>{
        this.error = err.message ||"univerzitet service puko"
        console.log(err)
      }
    })
  }
}
