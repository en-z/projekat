import { Component } from '@angular/core';
import { Izdate } from '../../models/izdate';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-izdate-put',
  imports: [CommonModule,FormsModule],
  templateUrl: './izdate-put.component.html',
  styleUrl: './izdate-put.component.css'
})
export class IzdatePutComponent {
  izdateList: Izdate[] = [];
  selectedIzdateId: number | null = null;
  noviDatumVracanja: string = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadIzdate();
  }

  loadIzdate() {
    this.http.get<Izdate[]>('http://localhost:8080/api/biblioteka/izdate/nul').subscribe(data => {
      this.izdateList = data;
    });
  }

  postaviDatum() {
    if (this.selectedIzdateId && this.noviDatumVracanja) {
      const payload = { datumVracanja: this.noviDatumVracanja };
      console.log(payload)

      this.http.put(`http://localhost:8080/api/biblioteka/izdate/${this.selectedIzdateId}`, payload).subscribe({
        next: () => {
          alert('Datum vraćanja uspešno postavljen!');
          this.loadIzdate();
          this.noviDatumVracanja = '';
          this.selectedIzdateId = null;
        },
        error: () => alert('Došlo je do greške pri postavljanju datuma')
      });
    } else {
      alert('Izaberite stavku i unesite datum vraćanja');
    }
  }
}
