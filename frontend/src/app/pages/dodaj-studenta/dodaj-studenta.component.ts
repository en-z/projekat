import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ZaUpis } from '../../models/ZaUpis';
import { ZaUpisService } from '../../services/zaupis.service';
import { PredmetService } from '../../services/predmet.service';
import { Predmet } from '../../models/predmet';
import { Page } from '../../models/Page';

@Component({
  selector: 'app-dodaj-studenta',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-studenta.component.html',
  styleUrl: './dodaj-studenta.component.css'
})

export class DodajStudentaComponent implements OnInit{
   zaUpisPage: Page<ZaUpis> | null = null;
  adresaForm: FormGroup;
  currentPage = 0;

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.adresaForm = this.fb.group({});
  }

  ngOnInit(): void {
    this.ucitajZaUpis();
  }

  ucitajZaUpis() {
    this.http.get<Page<ZaUpis>>(`http://localhost:8080/api/auth/zaupis/1?page=${this.currentPage}&size=5`)
      .subscribe(page => {
        this.zaUpisPage = page;

        // Resetuj formu da ne nosi podatke prethodne strane
        this.adresaForm = this.fb.group({});
        page.content.forEach(upis => {
          this.adresaForm.addControl(upis.userId + '_ulica', this.fb.control(''));
          this.adresaForm.addControl(upis.userId + '_broj', this.fb.control(''));
          this.adresaForm.addControl(upis.userId + '_grad', this.fb.control(''));
          this.adresaForm.addControl(upis.userId + '_drzava', this.fb.control(''));
        });
      });
  }

  loadPrevious() {
    if (this.zaUpisPage && !this.zaUpisPage.first) {
      this.currentPage--;
      this.ucitajZaUpis();
    }
  }

  loadNext() {
    if (this.zaUpisPage && !this.zaUpisPage.last) {
      this.currentPage++;
      this.ucitajZaUpis();
    }
  }

  submitAdrese() {
    if (!this.zaUpisPage) return;

    const adrese = this.zaUpisPage.content.map(upis => ({
      ime: upis.ime,
      prezime: upis.prezime,
      userId: upis.userId,
      studiskiId: upis.studiskiId,
      ulica: this.adresaForm.get(upis.userId + '_ulica')?.value,
      broj: this.adresaForm.get(upis.userId + '_broj')?.value,
      grad: this.adresaForm.get(upis.userId + '_grad')?.value,
      drzava: this.adresaForm.get(upis.userId + '_drzava')?.value,
    }));

    this.http.post('http://localhost:8080/api/student/studenti', adrese)
      .subscribe(res => {
        console.log("Uspešno poslato", res);
      });
  }
}
