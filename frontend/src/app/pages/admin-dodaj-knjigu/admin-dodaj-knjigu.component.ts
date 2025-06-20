import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { KnjigaService } from '../../services/knjiga.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-admin-dodaj-knjigu',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './admin-dodaj-knjigu.component.html',
  styleUrl: './admin-dodaj-knjigu.component.css'
})
export class AdminDodajKnjiguComponent {
  knjigaForm!: FormGroup;
  knjigaId!:number;
  constructor(private route:ActivatedRoute,private fb: FormBuilder,private knjigaService:KnjigaService) {}

  ngOnInit(): void {
    this.knjigaForm = this.fb.group({
      naziv: ['', Validators.required],
      kategorija: ['', Validators.required],
      opis: ['', Validators.required],
      godinaIzdavanja: [, Validators.required],
      autor: ['', Validators.required],
      kolicina: [0, [Validators.required, Validators.min(0)]],
    });
    this.route.paramMap.subscribe(params => {
    const id = params.get('id');
    if (id) {
      this.knjigaId = +id;
      this.knjigaService.getById(this.knjigaId).subscribe(knjiga => {
        this.knjigaForm.patchValue(knjiga);
      });
    }
  });
  }

  onSubmit() {
    if (this.knjigaForm.valid) {
      if (this.knjigaId) {
        this.knjigaService.update(this.knjigaId, this.knjigaForm.value).subscribe(() => {
          alert("Uspesno izmenjena knjiga");
        });
      } else {
        this.knjigaService.create(this.knjigaForm.value).subscribe(() => {
          alert("Uspesno dodata knjiga");
        });
      }
    } else {
      alert("Nije validna forma")
    }
  }
}
