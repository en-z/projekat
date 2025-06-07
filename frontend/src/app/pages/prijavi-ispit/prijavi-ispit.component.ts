import { Component,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Predmet } from '../../models/predmet';
import { PrijavaService } from '../../services/prijava.service';
import { PrijavaIspit } from '../../models/prijavaIspit';
import { HttpClient } from '@angular/common/http';
import { IspitniRokService } from '../../services/ispitniRok.service';
import { IspitniRok } from '../../models/ispitniRok';
import { PredmetService } from '../../services/predmet.service';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-prijavi-ispit',
  imports: [CommonModule,FormsModule],
  templateUrl: './prijavi-ispit.component.html',
  styleUrl: './prijavi-ispit.component.css'
})

export class PrijaviIspitComponent implements OnInit{
  predmeti:Predmet[]=[]
  rokovi:IspitniRok[]=[]
  selectedPredmet:Predmet|null = null
  selectedRok:IspitniRok|null = null
  aktivni:boolean=true;
  showWindow:boolean = false;
  constructor(private rokService:IspitniRokService,private predmetService:PredmetService,private prijavaService:PrijavaService){

  }
  ngOnInit(): void {
    this.loadRok()
    if(this.rokovi.length == 0){
      this.aktivni =false
    }
    this.loadPredmete()
    console.log(this.predmeti);
    console.log(this.rokovi);
  }
  loadPredmete(){
    this.predmetService.getPredmeteZaUpis().subscribe(data=>{this.predmeti = data,console.log(data)})
  }
  loadRok(){
    this.rokService.getAktivne().subscribe((data)=>{this.rokovi =data,
                                           console.log(data)})
  }
  odabirRokova(predmet:Predmet){
    this.selectedPredmet = predmet;
    this.selectedRok = null;
    this.showWindow = true;
  }
  submitPrijava(rok:IspitniRok|null){
    if (!this.selectedPredmet || !rok) return;
    var datumOdrzavanjaa = new Date(rok.pocetak)
    datumOdrzavanjaa.setDate(datumOdrzavanjaa.getDate()+this.selectedPredmet.dan!)
    const prijava: PrijavaIspit = {
      id: null,
      datumOdrzavanja:datumOdrzavanjaa,
      rok: rok.id!,
      predmetId: this.selectedPredmet.id,
      datumPrijave: new Date(),
    };

    this.prijavaService.create(prijava).subscribe({
      next: () => {
        this.showWindow= false;
      },
      error: (err) => {
        console.error('Error submitting prijava:', err);
      },
    });
  }
}

