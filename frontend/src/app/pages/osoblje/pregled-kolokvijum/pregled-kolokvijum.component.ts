import { Component } from '@angular/core';
import { StudiskiProgram } from '../../../models/StudiskiProgram';
import { Kolokvijum } from '../../../models/Kolokvijum';
import { StudiskiService } from '../../../services/studiski.service';
import { KolokvijumService } from '../../../models/kolokvijum.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PredmetService } from '../../../services/predmet.service';
import { Predmet } from '../../../models/predmet';

@Component({
  selector: 'app-pregled-kolokvijum',
  imports: [CommonModule,FormsModule],
  templateUrl: './pregled-kolokvijum.component.html',
  styleUrl: './pregled-kolokvijum.component.css'
})
export class PregledKolokvijumComponent {
  fakultetId = 1;
  programi: StudiskiProgram[] = [];
  izabraniProgramId!: number;
  kolokvijumi: Kolokvijum[] = [];
  mapaPredmeta = new Map<number, string>(); // predmetId -> naziv

  constructor(
    private programService: StudiskiService,
    private predmetService: PredmetService,
    private kolokvijumService:KolokvijumService
  ) {}

  ngOnInit(): void {
    this.programService.getByFakultet(this.fakultetId).subscribe(programi => {
      this.programi = programi;
    });
  }

  onProgramChange(): void {
    this.predmetService.getByProgram(this.fakultetId).subscribe(predmeti => {
      this.mapaPredmeta = new Map(predmeti.map(p => [p.id, p.naziv]));

      this.kolokvijumService.getByProgram(this.izabraniProgramId).subscribe(kolokvijumi => {
        this.kolokvijumi = kolokvijumi.map(k => ({
          ...k,
          predmetNaziv: this.mapaPredmeta.get(k.predmetId) || 'Nepoznat predmet'
        }));
      });
    });
  }

  formatDatum(d: string): string {
    return new Date(d).toLocaleString();
  }

}
