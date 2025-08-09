import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IshodIspitaService } from '../../../services/ishod.ispita.service';
import { IshodIspita } from '../../../models/ishod.ispita';
import { SlusanjePredmetaService } from '../../../services/slusanje.predmeta.service';
import { Student } from '../../../models/student';
import { HttpClient } from '@angular/common/http';
import { InstrumentEvaluacijeService } from '../../../services/instrument.evaluacije.service';
import { InstrumentEvaluacije } from '../../../models/instrument.evaluacije';
import { IspitniRokService } from '../../../services/ispitniRok.service';
import { PrijavaService } from '../../../services/prijava.service';
import { StudentService } from '../../../services/student.service';
import { KolokvijumRezultatService } from '../../../services/kolokvijumRezultat.service';
import { KolokvijumRezultat } from '../../../models/kolokvijumRezultat';


@Component({
  selector: 'app-unos-ocena',
  imports: [CommonModule, FormsModule],
  templateUrl: './unos-ocena.component.html',
  styleUrls: ['./unos-ocena.component.css']
})
export class UnosOcenaComponent implements OnInit {
  predmetId!: number;
  rokovi: any[] = [];
  izabraniRokId!: number;
  studenti: any[] = [];
  dozvoljenUnos:boolean = false;
  instrumenti:InstrumentEvaluacije[]=[]
  instrumentId = 1;
  openedRow: number | null = null;
  tabela: IshodIspita[] = [];
  kolokvijumiStudenta:KolokvijumRezultat[]=[]
  kolokvijumiMapa: { [index: number]: KolokvijumRezultat[] } = {};

  constructor(
    private ispitniRokService: IspitniRokService,
    private instrumentService:InstrumentEvaluacijeService,
    private studenService:StudentService,
    private ishodService:IshodIspitaService,
    private kolokvijumService:KolokvijumRezultatService,
    private route:ActivatedRoute,
  ) {}

  ngOnInit() {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajRokove();
    this.ishodService.proveriStatusUnosaOcene(this.predmetId).subscribe(res => {
      this.dozvoljenUnos = res;
    });
    this.instrumentService.getByPredmetId(this.predmetId).subscribe(data=>{
      this.instrumenti= data;
    })
  }

  ucitajRokove() {
    this.ispitniRokService.getAktivne().subscribe(rokovi => {
      this.rokovi = rokovi;
    });
  }

  onRokChange() {
    if (!this.izabraniRokId) return;
    this.studenService.getByPredmetIdAndRok(this.izabraniRokId,this.predmetId).subscribe(studenti => {
      this.studenti = studenti;
      this.tabela = studenti.map(s => ({
        id: null,
        brojPokusaja: null,
        rokId: this.izabraniRokId,
        kolokvijumiId: [],
        polozen: false,
        bodovi: 0,
        ocena: 0,
        datumUnosa: '',
        studentId: s.id,
        predmetId: this.predmetId,
        nastavnikId: null,
        instumentId: this.instrumentId
      }));
    });
  }
  ucitajKolokvijume(index: number, studentId: number) {
  if (this.openedRow === index) {
    this.openedRow = null;
    return;
  }

  this.kolokvijumService.getRezultateStudentaIPredmeta(this.predmetId, studentId)
    .subscribe(kolokvijumi => {
      this.kolokvijumiMapa[index]= kolokvijumi;
      this.openedRow = index;
    });
  }

  toggleKolokvijum(red: IshodIspita, kolokvijumId: number, event: Event) {
    const checked = (event.target as HTMLInputElement).checked;
    if (checked) {
      red.kolokvijumiId.push(kolokvijumId);
    } else {
      red.kolokvijumiId = red.kolokvijumiId.filter(id => id !== kolokvijumId);
    }
  }
  sacuvaj() {
    this.ishodService.create(this.tabela).subscribe(data=>{
      console.log("dodato")
    })
  }
}
