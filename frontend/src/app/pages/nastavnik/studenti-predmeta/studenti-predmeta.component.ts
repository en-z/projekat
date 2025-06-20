import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SlusanjePredmeta } from '../../../models/slusanje.predmeta';
import { SlusanjePredmetaService } from '../../../services/slusanje.predmeta.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-studenti-predmeta',
  imports: [CommonModule],
  templateUrl: './studenti-predmeta.component.html',
  styleUrls: ['./studenti-predmeta.component.css']
})
export class StudentiPredmetaComponent implements OnInit {
  predmetId!: number;
  studenti: SlusanjePredmeta[] = [];

  constructor(
    private route: ActivatedRoute,
    private slusanjeService: SlusanjePredmetaService
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.slusanjeService.getByPredmet(this.predmetId).subscribe(data => {
      this.studenti = data;
    });
  }
  ucitajStudente(){
  this.slusanjeService.getByPredmet(this.predmetId).subscribe(data => {
    this.studenti = data;
  });
  }
}
