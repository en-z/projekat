import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Obavestenje } from '../../../../models/obavestenje';
import { ObavestenjeService } from '../../../../services/obavestenje.service';

@Component({
  selector: 'app-obavestenja',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './obavestenja.component.html',
  styleUrls: ['./obavestenja.component.css']
})
export class ObavestenjaComponent implements OnInit {
  predmetId!: number;
  nastavnikId: number = 1; // isto menjaj kad uradis logijn
  obavestenja: Obavestenje[] = [];
  novo: Partial<Obavestenje> = { naslov: '', tekst: '' };
  uIzmeniId: number | null = null;
  izmena: Partial<Obavestenje> = { naslov: '', tekst: '' };

  constructor(
    private route: ActivatedRoute,
    private obavestenjeService: ObavestenjeService
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajObavestenja();
  }

  ucitajObavestenja(): void {
    this.obavestenjeService.getByPredmet(this.predmetId).subscribe({
      next: data => {
        this.obavestenja = data.map(o => ({
          ...o,
          datumPostavljanja: new Date(o.datumPostavljanja!)
        }));
      },
      error: err => console.error('Greška pri dohvatu obaveštenja', err)
    });
  }


  dodaj(): void {
    if (!this.novo.naslov || !this.novo.tekst) return;

    const novoObavestenje: Obavestenje = {
        id: 0,
        naslov: this.novo.naslov!,
        tekst: this.novo.tekst!,
        predmetId: this.predmetId,
        nastavnikId: this.nastavnikId,
        datumPostavljanja: null,
        ime: '',
        prezime: ''
    };

    this.obavestenjeService.create(novoObavestenje).subscribe({
      next: () => {
        this.ucitajObavestenja();
        this.novo = { naslov: '', tekst: '' };
      },
      error: err => console.error('Greška pri dodavanju obaveštenja', err)
    });
  }

  obrisi(id: number): void {
    this.obavestenjeService.delete(id).subscribe({
      next: () => this.ucitajObavestenja(),
      error: err => console.error('Greška pri brisanju obaveštenja', err)
    });
  }

  pokreniIzmenu(o: Obavestenje): void {
    this.uIzmeniId = o.id;
    this.izmena = { naslov: o.naslov, tekst: o.tekst };
  }

  sacuvajIzmenu(): void {
    if (this.uIzmeniId === null) return;

    const azurirano: Obavestenje = {
        id: this.uIzmeniId,
        naslov: this.izmena.naslov!,
        tekst: this.izmena.tekst!,
        datumPostavljanja: null,
        predmetId: this.predmetId,
        nastavnikId: this.nastavnikId,
        ime: '',
        prezime: ''
    };

    this.obavestenjeService.update(this.uIzmeniId, azurirano).subscribe({
      next: () => {
        this.uIzmeniId = null;
        this.ucitajObavestenja();
      },
      error: err => console.error('Greška pri izmeni obaveštenja', err)
    });
  }

  otkaziIzmenu(): void {
    this.uIzmeniId = null;
    this.izmena = { naslov: '', tekst: '' };
  }
}
