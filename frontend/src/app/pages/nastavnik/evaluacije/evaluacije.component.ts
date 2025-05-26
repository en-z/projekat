import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InstrumentEvaluacije } from '../../../models/instrument.evaluacije';
import { InstrumentEvaluacijeService } from '../../../services/instrument.evaluacije.service';

@Component({
  selector: 'app-evaluacije',
  imports: [CommonModule, FormsModule],
  templateUrl: './evaluacije.component.html',
  styleUrls: ['./evaluacije.component.css']
})
export class EvaluacijeComponent implements OnInit {
  predmetId!: number;
  nastavnikId: number = 1; // zameni sa ID iz tokena kasnije
  evaluacije: InstrumentEvaluacije[] = [];

  novaEvaluacija: Partial<InstrumentEvaluacije> = { tip: '', opis: '' };
  uIzmeniId: number | null = null;
  izmenaEvaluacija: Partial<InstrumentEvaluacije> = { tip: '', opis: '' };

  constructor(
    private route: ActivatedRoute,
    private evaluacijeService: InstrumentEvaluacijeService
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajEvaluacije();
  }

  ucitajEvaluacije(): void {
    this.evaluacijeService.getByPredmetId(this.predmetId).subscribe({
      next: data => this.evaluacije = data,
      error: err => console.error('Greška pri dohvatu evaluacija', err)
    });
  }

  dodajEvaluaciju(): void {
    if (!this.novaEvaluacija.tip || !this.novaEvaluacija.opis) {
      alert('Unesi tip i opis evaluacije');
      return;
    }

    const nova: InstrumentEvaluacije = {
      id: 0,
      tip: this.novaEvaluacija.tip!,
      opis: this.novaEvaluacija.opis!,
      predmetId: this.predmetId,
      nastavnikId: this.nastavnikId
    };

    this.evaluacijeService.create(nova).subscribe({
      next: () => {
        this.ucitajEvaluacije();
        this.novaEvaluacija = { tip: '', opis: '' };
      },
      error: err => console.error('Greška pri dodavanju evaluacije', err)
    });
  }

  obrisiEvaluaciju(id: number): void {
    this.evaluacijeService.delete(id).subscribe({
      next: () => this.ucitajEvaluacije(),
      error: err => console.error('Greška pri brisanju evaluacije', err)
    });
  }

  pokreniIzmenu(e: InstrumentEvaluacije): void {
    this.uIzmeniId = e.id;
    this.izmenaEvaluacija = { tip: e.tip, opis: e.opis };
  }

  sacuvajIzmenu(): void {
    if (this.uIzmeniId === null) return;

    const azurirano: InstrumentEvaluacije = {
      id: this.uIzmeniId,
      tip: this.izmenaEvaluacija.tip!,
      opis: this.izmenaEvaluacija.opis!,
      predmetId: this.predmetId,
      nastavnikId: this.nastavnikId
    };

    this.evaluacijeService.update(this.uIzmeniId, azurirano).subscribe({
      next: () => {
        this.ucitajEvaluacije();
        this.uIzmeniId = null;
      },
      error: err => console.error('Greška pri izmeni evaluacije', err)
    });
  }

  otkaziIzmenu(): void {
    this.uIzmeniId = null;
    this.izmenaEvaluacija = { tip: '', opis: '' };
  }
}
