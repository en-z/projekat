import { Component, OnInit } from '@angular/core';
import { Raspored } from '../../../models/raspored';
import { StudiskiProgram } from '../../../models/StudiskiProgram';
import { StudentService } from '../../../services/student.service';
import { PredmetService } from '../../../services/predmet.service';
import { RasporedService } from '../../../services/raspored.service';
import { StudiskiService } from '../../../services/studiski.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pregled-raspored',
  imports: [CommonModule,FormsModule],
  templateUrl: './pregled-raspored.component.html',
  styleUrl: './pregled-raspored.component.css'
})
export class PregledRasporedComponent implements OnInit{
  fakultetId = 1;
  programi: StudiskiProgram[] = [];
  izabraniProgramId: number=0;
  rasporedi: Raspored[] = [];
  mapaPredmeta = new Map<number, string>();

  constructor(
    private programService: StudiskiService,
    private predmetService: PredmetService,
    private rasporedService:RasporedService
  ) {}

  ngOnInit(): void {
    this.programService.getByFakultet(this.fakultetId).subscribe(programi => {
      this.programi = programi;
    });
  }

  onProgramChange(): void {
    this.predmetService.getByProgram(this.fakultetId).subscribe(predmeti => {
      this.mapaPredmeta = new Map(predmeti.map(p => [p.id, p.naziv]));

      this.rasporedService.getByProgram(this.izabraniProgramId).subscribe(rasporedi => {
        this.rasporedi = rasporedi
          .map(r => ({
            ...r,
            predmetNaziv: this.mapaPredmeta.get(r.predmetId) || 'Nepoznat predmet'
          }))
          .sort((a, b) => a.semestar - b.semestar || a.dan - b.dan || a.vreme - b.vreme);
      });
    });
  }
}
