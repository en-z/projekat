import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IshodIspitaService } from '../../../services/ishod.ispita.service';
import { IshodIspita } from '../../../models/ishod.ispita';
import { SlusanjePredmetaService } from '../../../services/slusanje.predmeta.service';
import { Student } from '../../../models/student';


@Component({
  selector: 'app-unos-ocena',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './unos-ocena.component.html',
  styleUrls: ['./unos-ocena.component.css']
})
export class UnosOcenaComponent implements OnInit {
  ocenaForm!: FormGroup;
  predmetId!: number;
  ishodi: IshodIspita[] = [];
  dozvoljenUnos: boolean = true;
  studenti: Student[] = [];

  constructor(
    private route: ActivatedRoute,
    private ishodService: IshodIspitaService,
    private slusanjeService: SlusanjePredmetaService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
  this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
  this.ocenaForm = this.fb.group({
    ocena: [null, [Validators.required, Validators.min(5), Validators.max(10)]],
    brojPokusaja: [null, Validators.required],
    bodovi: [0, Validators.required],
    polozen: [false, Validators.required],
    studentId: [null, Validators.required],
    nastavnikId: [1], // zameni sa dijem iz tokena
    predmetId: [this.predmetId]
  });

  this.ishodService.proveriStatusUnosaOcene(this.predmetId).subscribe(res => {
    this.dozvoljenUnos = res;
  });

  this.ucitajIshode();
  this.ucitajStudente();
}

ucitajStudente(): void {
  this.slusanjeService.getByPredmet(this.predmetId).subscribe(data => {
    this.studenti = data.map(s => s.student);
  });
}


  ucitajIshode(): void {
    this.ishodService.getByPredmet(this.predmetId).subscribe(data => {
      this.ishodi = data;
    });
  }

  submit(): void {
    if (this.ocenaForm.valid) {
      this.ishodService.create(this.ocenaForm.value).subscribe({
        next: () => {
          alert('Uspesno uneta ocena!');
          this.ocenaForm.reset();
          this.ocenaForm.get('predmetId')?.setValue(this.predmetId);
          this.ucitajIshode();
        },
        error: () => alert('Greška pri unosu.')
      });
    }
  }
}
