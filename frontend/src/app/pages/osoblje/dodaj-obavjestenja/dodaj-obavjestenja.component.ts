import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { OpstaObavjestenja } from '../../../models/opstaObavjestenja';
import { OpstaObavjestenjaService } from '../../../services/opstaObavjestenja.service';

@Component({
  selector: 'app-dodaj-obavjestenja',
  imports: [CommonModule,FormsModule],
  templateUrl: './dodaj-obavjestenja.component.html',
  styleUrl: './dodaj-obavjestenja.component.css'
})
export class DodajObavjestenjaComponent {
  obavestenje: OpstaObavjestenja= {
    naslov: '',
    text: '',
    godina: new Date().getFullYear(),
    mesec: new Date().getMonth() + 1,
    dan: new Date().getDate(),
    fakultetID: 1,
  };
  constructor(private obavestenjeService:OpstaObavjestenjaService){}

  sacuvajVest() {
    this.obavestenjeService.post(this.obavestenje).subscribe({
      next: (res) => {
        console.log('Uspešno sačuvana vest:', res);
      },
      error: (err) => {
        console.error('Greška pri slanju vesti:', err);
      }
    });
  }
}
