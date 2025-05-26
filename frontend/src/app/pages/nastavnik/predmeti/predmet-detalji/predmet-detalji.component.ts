import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PredmetService } from '../../../../services/predmet.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-predmet-detalji',
  imports: [CommonModule],
  templateUrl: './predmet-detalji.component.html',
  styleUrls: ['./predmet-detalji.component.css']
})
export class PredmetDetaljiComponent implements OnInit {
  predmetId!: number;
  predmet: any;

  constructor(
    private route: ActivatedRoute,
    private predmetService: PredmetService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.ucitajPredmet();
  }

  ucitajPredmet(): void {
    this.predmetService.getById(this.predmetId).subscribe({
      next: data => this.predmet = data,
      error: err => console.error('Greška prilikom učitavanja predmeta', err)
    });
  }

  idiNa(path: string): void {
    this.router.navigate([`/nastavnik/predmet/${this.predmetId}/${path}`]);
  }

  otvoriListuStudenata(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['/nastavnik/predmet', id, 'studenti']);
  }
}
