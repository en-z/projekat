import { Component } from '@angular/core';
import { Knjiga } from '../../models/knjiga';
import { KnjigaService } from '../../services/knjiga.service';
import { Izdate } from '../../models/izdate';
import { IzdateService } from '../../services/izdate.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pocetna',
  imports: [CommonModule,FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrl: './pocetna.component.css'
})
export class PocetnaComponent {
 knjige: Knjiga[] = [];
  sveKnjige: Knjiga[] = [];

  kategorije: string[] = ['Inzinjerstvo', 'Istorija', 'Matematika', 'Ekonomija','Financije'];
  izabranaKategorija: string = '';

  pretraga: string = '';
  filterPolje: string[] = ['naziv','opis','autor'];
  izabranoPolje:string= 'naziv'

  constructor(private knjigaService: KnjigaService,private izdateService:IzdateService) {}

  ngOnInit(): void {
    this.ucitajSveKnjige();
  }

  ucitajSveKnjige() {
    this.knjigaService.getAll().subscribe(data=> {
      this.knjige = data;
      this.sveKnjige = data;
    });
  }

  filtrirajPoKategoriji() {
    if (this.izabranaKategorija === '') {
      this.knjige = this.sveKnjige;
    } else {
      this.knjigaService.getByKategorija(this.izabranaKategorija).subscribe(knjige => {
        this.knjige = knjige;
      });
    }
  }
pretrazi() {
  const searchParams: any = {};

  if (this.izabranaKategorija) {
    if(this.izabranaKategorija == "Sve")this.izabranaKategorija == null
    searchParams.kategorija = this.izabranaKategorija;
  }

  if (this.pretraga && this.izabranoPolje) {
    searchParams[this.izabranoPolje] = this.pretraga;
  }

  this.knjigaService.searchKnjige(searchParams).subscribe(knjige => {
    this.knjige = knjige;
  });
}


  resetujPretragu() {
    this.pretraga = '';
    this.filtrirajPoKategoriji();
  }
  izdaj(knjigaId:number){
    this.izdateService.izdaj(knjigaId)
  }
}
