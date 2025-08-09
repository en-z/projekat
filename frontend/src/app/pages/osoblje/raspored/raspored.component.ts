import { Component } from '@angular/core';
import { StudiskiProgram } from '../../../models/StudiskiProgram';
import { Predmet } from '../../../models/predmet';
import { StudiskiService } from '../../../services/studiski.service';
import { PredmetService } from '../../../services/predmet.service';
import { Raspored } from '../../../models/raspored';
import { RasporedService } from '../../../services/raspored.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-raspored',
  imports: [CommonModule,FormsModule],
  templateUrl: './raspored.component.html',
  styleUrl: './raspored.component.css'
})
export class RasporedComponent {
  programi: StudiskiProgram[] = [];
  izabraniProgramId!: number;
  predmeti: Predmet[] = [];
  datumOd = '';
  datumDo = '';
  semestar = 1;

  raspored: Raspored[] = [];

  constructor(
    private programService: StudiskiService,
    private predmetService: PredmetService,
    private rasporedService: RasporedService
  ) {}

  ngOnInit(): void {
    this.programService.getByFakultet(1).subscribe(programi => {
      this.programi = programi;
    });
  }

  onProgramChange(): void {
    this.predmetService.getByProgram(this.izabraniProgramId).subscribe(predmeti => {
      this.predmeti = predmeti;

      this.raspored= this.predmeti.map(predmet => ({
        datumOd: this.datumOd,
        datumDo: this.datumDo,
        programId: this.izabraniProgramId,
        semestar: this.semestar,
        predmetId: predmet.id,
        dan: 1,
        vreme: 8.0
      }));
    });
  }

  posalji(): void {
    this.rasporedService.kreirajRaspored(this.raspored).subscribe({
    });
  }

  updateDan(index: number, dan: number) {
    this.raspored[index].dan = dan;
  }

  updateVreme(index: number, vreme: number) {
    this.raspored[index].vreme = vreme;
  }
}
