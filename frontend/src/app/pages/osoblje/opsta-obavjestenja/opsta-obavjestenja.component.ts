import { Component, OnInit } from '@angular/core';
import { OpstaObavjestenja } from '../../../models/opstaObavjestenja';
import { OpstaObavjestenjaService } from '../../../services/opstaObavjestenja.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-opsta-obavjestenja',
  imports: [CommonModule],
  templateUrl: './opsta-obavjestenja.component.html',
  styleUrl: './opsta-obavjestenja.component.css'
})
export class OpstaObavjestenjaComponent implements OnInit{
 obavjestenja: OpstaObavjestenja[] = [];
  fakultetId = 1;
  godine = [2023, 2024, 2025];
  meseci = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  izabranaGodina: number | null = null;

  constructor(private obavjestenjaService: OpstaObavjestenjaService) {}

  ngOnInit(): void {
    this.ucitajTop10();
  }

  ucitajTop10() {
    this.obavjestenjaService.getTop10(this.fakultetId).subscribe(data => {
      this.obavjestenja = data;
    });
  }

  onGodinaClick(godina: number) {
    this.izabranaGodina = godina;
  }

  onMesecClick(mesec: number) {
    if (this.izabranaGodina != null) {
      this.obavjestenjaService
        .getByFakultetMesecGodina(this.fakultetId, this.izabranaGodina, mesec)
        .subscribe(data => {
          this.obavjestenja = data;
        });
    }
  }
}
