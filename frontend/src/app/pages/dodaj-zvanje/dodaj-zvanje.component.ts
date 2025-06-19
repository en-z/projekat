import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-dodaj-zvanje',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-zvanje.component.html',
  styleUrl: './dodaj-zvanje.component.css'
})
export class DodajZvanjeComponent implements OnInit{
  zvanjeForm!: FormGroup;
  nastavnici: any[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.zvanjeForm = this.fb.group({
      naziv: ['', Validators.required],
      datumIzbora: ['', Validators.required],
      datumPrestanka: ['', Validators.required],
      nastavnikId: [null, Validators.required]
    });

    this.http.get<any[]>('http://localhost:8080/api/nastavnik/nastavnici/bez-zvanja').subscribe(data => {
      this.nastavnici = data;
    });
  }

  onSubmit(): void {
    if (this.zvanjeForm.valid) {
      this.http.post('http://localhost:8080/api/nastavnik/zvanje', this.zvanjeForm.value).subscribe({
        next: res => {
          alert('Zvanje uspješno dodato.');
          this.http.get<any[]>('http://localhost:8080/api/nastavnik/nastavnici/bez-zvanja').subscribe(data => {
            this.nastavnici = data;
          });
          this.zvanjeForm.reset();
        },
        error: err => {
          alert('Greška pri dodavanju zvanja.');
        }
      });
    }
  }
}
