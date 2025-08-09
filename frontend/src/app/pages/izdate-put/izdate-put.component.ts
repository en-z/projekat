import { Component } from '@angular/core';
import { Izdate } from '../../models/izdate';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Zahtjev } from '../../models/zahtjev';
import { IzdateService } from '../../services/izdate.service';
import { ZahtjevService } from '../../services/Zahtjev.service';

@Component({
  selector: 'app-izdate-put',
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './izdate-put.component.html',
  styleUrl: './izdate-put.component.css'
})
export class IzdatePutComponent {
  searchForm = new FormGroup({
    userIdMin: new FormControl(null),
    userIdMax: new FormControl(null),
    imeContains: new FormControl(''),
    prezimeContains: new FormControl(''),

    knjigaNazivContains: new FormControl(''),
    knjigaKategorijaContains: new FormControl(''),
    knjigaOpisContains: new FormControl(''),
    knjigaGodinaIzdavanjaMin: new FormControl(null),
    knjigaGodinaIzdavanjaMax: new FormControl(null),
    knjigaAutorContains: new FormControl(''),
    knjigaKolicinaMin: new FormControl(null),
    knjigaKolicinaMax: new FormControl(null),
  });

  rezultati: Zahtjev[] = [];
  selektovani?: Zahtjev;

  trajan: boolean = false;
  datumVracanja?: string;

  constructor(private zahtjevService:ZahtjevService,private izdaj:IzdateService) {}

  pretrazi() {
    // Uzmi vrednosti iz forme i prosledi kao searchCriteria
    const searchCriteria = this.searchForm.value;
    this.zahtjevService.search(searchCriteria).subscribe(res => {
      this.rezultati = res;
    });
  }

  selektuj(zahtjev: Zahtjev) {
    this.selektovani = zahtjev;
    this.trajan = false;
    this.datumVracanja = undefined;
  }

  potvrdiIzdavanje() {
    if (!this.selektovani) return;

    const dto: Izdate= {
      knjigaId: this.selektovani.knjiga.id!,
      userId: this.selektovani.userId,
      ime: this.selektovani.ime,
      prezime: this.selektovani.prezime,
      trajan: this.trajan,
      datumVracanja: this.trajan ? null : this.datumVracanja || null
    };

    this.izdaj.izdaj(dto,this.selektovani.id).subscribe(() => {
      this.selektovani = undefined;
      this.trajan = false;
      this.datumVracanja = undefined;
      this.pretrazi();
    });
  }
}

