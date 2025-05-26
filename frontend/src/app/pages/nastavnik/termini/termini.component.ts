import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Termin } from '../../../models/termin';
import { TerminService } from '../../../services/termin.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-termini',
  imports: [CommonModule, FormsModule],
  templateUrl: './termini.component.html',
  styleUrls: ['./termini.component.css']
})
export class TerminiComponent implements OnInit {
  predmetId!: number;
  nastavnikId: number = 1; // ovo zameni posle sa idjem iz tokenaa
  termini: Termin[] = [];
  noviTermin: Partial<Termin> = { tema: '', datum: undefined };
  uIzmeniId: number | null = null;
  izmenaTermin: Partial<Termin> = { tema: '', datum: undefined };
  minDateTime: string = '';

  constructor(
    private route: ActivatedRoute,
    private terminService: TerminService
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajTermine();

    const now = new Date();
    now.setSeconds(0, 0);
    this.minDateTime = now.toISOString().slice(0, 16);
  }

  ucitajTermine(): void {
    this.terminService.getByPredmet(this.predmetId).subscribe({
      next: data => this.termini = data,
      error: err => console.error('Greška pri dohvatu termina', err)
    });
  }

  dodajTermin(): void {

    if (!this.noviTermin.tema || !this.noviTermin.datum) {
      alert('Unesi i temu i datum termina.');
      return;
    }

    const novi: any = {
      id: 0,
      tema: this.noviTermin.tema!,
      datum: this.toISOStringLocal(this.noviTermin.datum!),
      predmetId: this.predmetId,
      nastavnikId: this.nastavnikId
    };

    this.terminService.create(novi).subscribe({
      next: () => {
        this.ucitajTermine();
        this.noviTermin = { tema: '', datum: undefined };
      },
      error: err => console.error('Greška pri dodavanju termina', err)
    });
  }



  obrisiTermin(id: number): void {
    this.terminService.delete(id).subscribe({
      next: () => this.ucitajTermine(),
      error: err => console.error('Greška pri brisanju termina', err)
    });
  }

  pokreniIzmenu(termin: Termin): void {
    this.uIzmeniId = termin.id;
    this.izmenaTermin = { tema: termin.tema, datum: termin.datum };
  }

  sacuvajIzmenu(): void {
    if (this.uIzmeniId === null) return;

    const azuriran: any = {
      id: this.uIzmeniId,
      tema: this.izmenaTermin.tema!,
      datum: this.toISOStringLocal(this.izmenaTermin.datum!),
      predmetId: this.predmetId,
      nastavnikId: this.nastavnikId
    };

    this.terminService.update(this.uIzmeniId, azuriran).subscribe({
      next: () => {
        this.uIzmeniId = null;
        this.ucitajTermine();
      },
      error: err => console.error('Greška pri izmeni termina', err)
    });
  }



  otkaziIzmenu(): void {
    this.uIzmeniId = null;
    this.izmenaTermin = { tema: '', datum: undefined };
  }

  toISOStringLocal(raw: string | Date): string {
  const date = typeof raw === 'string' ? new Date(raw) : raw;

  if (isNaN(date.getTime())) {
    console.error('Nevalidan datum:', raw);
    return '';
  }

  const tzOffset = date.getTimezoneOffset() * 60000;
  return new Date(date.getTime() - tzOffset).toISOString().slice(0, 16);
}



}
