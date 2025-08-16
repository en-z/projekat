import { Component } from '@angular/core';
import { Knjiga } from '../../models/knjiga';
import { KnjigaService } from '../../services/knjiga.service';
import { Izdate } from '../../models/izdate';
import { IzdateService } from '../../services/izdate.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pocetna',
  imports: [ReactiveFormsModule,CommonModule,FormsModule],
  templateUrl: './pocetna.component.html',
  styleUrl: './pocetna.component.css'
})
export class PocetnaComponent {
  knjige: Knjiga[] = [];
  sveKnjige: Knjiga[] = [];

  // Glavni filter
  naziv: string = '';

  // Advanced filter
  kategorije: string[] = ['Inzinjerstvo', 'Istorija', 'Matematika', 'Ekonomija','Financije'];
  izabranaKategorija: string = '';
  opis: string = '';
  autor: string = '';
  kolicinaOd?: number;
  kolicinaDo?: number;
  godinaOd?: number;
  godinaDo?: number;

  showAdvanced: boolean = false;

  constructor(private router:Router, public authService: AuthService, private knjigaService: KnjigaService, private izdateService: IzdateService) {}

  ngOnInit(): void {
    this.ucitajSveKnjige();
  }

  ucitajSveKnjige() {
    this.knjigaService.getAll().subscribe(data=> {
      this.knjige = data;
      this.sveKnjige = data;
    });
  }

  pretrazi() {
    const params: any = {};

    if (this.naziv) params.naziv = this.naziv;
    if (this.izabranaKategorija) params.kategorija = this.izabranaKategorija;
    if (this.opis) params.opis = this.opis;
    if (this.autor) params.autor = this.autor;
    if (this.kolicinaOd != null) params.kolicinaOd = this.kolicinaOd;
    if (this.kolicinaDo != null) params.kolicinaDo = this.kolicinaDo;
    if (this.godinaOd != null) params.godinaOd = this.godinaOd;
    if (this.godinaDo != null) params.godinaDo = this.godinaDo;

    this.knjigaService.searchKnjige(params).subscribe(knjige => {
      this.knjige = knjige;
    });
  }

  resetujPretragu() {
    this.naziv = '';
    this.izabranaKategorija = '';
    this.opis = '';
    this.autor = '';
    this.kolicinaOd = undefined;
    this.kolicinaDo = undefined;
    this.godinaOd = undefined;
    this.godinaDo = undefined;
    this.ucitajSveKnjige();
  }

  toggleAdvanced() {
    this.showAdvanced = !this.showAdvanced;
  }

  izdaj(knjigaId: number) {
    this.izdateService.zahtjev(knjigaId).subscribe(() => console.log());
  }

  edit(knjigaId: number) {
    this.router.navigate([`/biblioteka/knjiga/${knjigaId}`]);
  }

  add() {
    this.router.navigate(['/biblioteka/knjiga']);
  }
}

