import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SilabusService } from '../../../services/silabus.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-silabus',
  imports: [CommonModule, FormsModule],
  templateUrl: './silabus.component.html',
  styleUrls: ['./silabus.component.css']
})
export class SilabusComponent implements OnInit {
  predmetId!: number;
  silabus: any;
  uredi: boolean = false;
  noviSadrzaj: string = '';

  constructor(
    private route: ActivatedRoute,
    private silabusService: SilabusService
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajSilabus();
  }

  ucitajSilabus(): void {
    this.silabusService.getByPredmetId(this.predmetId).subscribe({
      next: data => {
        this.silabus = data;
        this.noviSadrzaj = data.sadrzaj;
      },
      error: err => console.error('Greška prilikom dohvata silabusa', err)
    });
  }

  sacuvajIzmene(): void {
    const dto = {
      id: this.silabus.id,
      sadrzaj: this.noviSadrzaj,
      predmetId: this.silabus.predmetId,
      nastavnikId: this.silabus.nastavnikId
    };

    this.silabusService.update(this.silabus.id, dto).subscribe({
    next: (updated) => {
      this.silabus = updated; // ažuriraš prikaz
      this.noviSadrzaj = updated.sadrzaj; // osvežavaš sadržaj u tekstareji (ako treba)
      this.uredi = false; // izlaziš iz uređivanja
    },
    error: err => console.error('Greška prilikom izmene silabusa', err)
  });
  }
}
