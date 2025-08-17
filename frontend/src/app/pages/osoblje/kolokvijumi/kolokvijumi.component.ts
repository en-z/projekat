import { Component } from '@angular/core';
import { StudiskiProgram } from '../../../models/StudiskiProgram';
import { Predmet } from '../../../models/predmet';
import { Kolokvijum } from '../../../models/Kolokvijum';
import { StudiskiService } from '../../../services/studiski.service';
import { PredmetService } from '../../../services/predmet.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { KolokvijumService } from '../../../models/kolokvijum.service';

@Component({
  selector: 'app-kolokvijumi',
  imports: [CommonModule,FormsModule],
  templateUrl: './kolokvijumi.component.html',
  styleUrl: './kolokvijumi.component.css'
})
export class KolokvijumiComponent {
  fakultetId = 1; // može doći iz route parametara
  programi: StudiskiProgram[] = [];
  izabraniProgramId!: number;
  predmeti: Predmet[] = [];

  kolokvijumi: Kolokvijum[] = [];

  constructor(
    private programService: StudiskiService,
    private predmetService: PredmetService,
    private kolokvijumService:KolokvijumService
  ) {}

  ngOnInit(): void {
    this.programService.getByFakultet(this.fakultetId).subscribe(programi => {
      this.programi = programi;
      console.log(programi)
    });
  }

  onProgramChange(): void {
    this.predmetService.getByProgram(this.izabraniProgramId).subscribe(predmeti => {
      this.predmeti = predmeti;

      this.kolokvijumi = this.predmeti.map(predmet => ({
        programId: this.izabraniProgramId,
        predmetId: predmet.id,
        naziv:'',
        ucionica: '',
        datum: ''
      }));
    });
  }

  posalji(): void {
    this.kolokvijumService.kreirajKolokvijume(this.kolokvijumi).subscribe({
    });
  }
}
