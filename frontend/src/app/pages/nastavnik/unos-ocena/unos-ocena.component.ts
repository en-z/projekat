import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { IshodIspitaService } from '../../../services/ishod.ispita.service';
import { IshodIspita } from '../../../models/ishod.ispita';
import { SlusanjePredmetaService } from '../../../services/slusanje.predmeta.service';
import { Student } from '../../../models/student';
import { HttpClient } from '@angular/common/http';


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
    private fb: FormBuilder,
    private http:HttpClient
  ) {}

  ngOnInit(): void {
  this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
  this.ocenaForm = this.fb.group({
    brojPokusaja: [null, Validators.required],
    bodovi: [0, Validators.required],
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
      this.ocene();
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
  ocene(){
    for (let val of this.ishodi){
      val.ocena = this.getOcena(val.bodovi)
    }
  }
  getOcena(n:number):number{
    if(n<51){
      return 5
    }
    return Math.floor((n-1)/10)+1;
  }
  selectedFile: File | null = null;

onFileSelected(event: Event): void {
  const input = event.target as HTMLInputElement;
  if (input.files && input.files.length > 0) {
    this.selectedFile = input.files[0];
  }
}

uploadFile(): void {
  if (!this.selectedFile) return;

  const formData = new FormData();
  formData.append('file', this.selectedFile);

  this.http.post('http://localhost:8080/api/nastavnik/ishodi/xml-file', formData).subscribe({
    next: (response) => {
      alert('Fajl je uspešno poslat.');
    },
    error: (error) => {
      console.error('Greška pri slanju fajla', error);
      alert('Došlo je do greške pri slanju fajla.');
    }
  });
}
