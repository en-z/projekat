import { Component, OnInit } from '@angular/core';
import { NastavnikService } from '../../../../services/nastavnik.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-nastavnik-predmeti',
  imports: [CommonModule, RouterModule],
  templateUrl: './nastavnik-predmeti.component.html',
  styleUrls: ['./nastavnik-predmeti.component.css']
})
export class NastavnikPredmetiComponent implements OnInit {
  predmeti: any[] = [];
  nastavnikId: number = 1; // Ovo bi trebalo da bude dinamički postavljeno, npr. iz servisa ili auth-a
  constructor(private nastavnikService: NastavnikService) {}

  ngOnInit(): void {
    this.ucitajPredmete();
  }

  ucitajPredmete(): void {
    this.nastavnikService.getPredmeti(this.nastavnikId).subscribe({
      next: (data) =>{
        this.predmeti = data
        console.log(data)
      },

      error: (err) => console.error('Greška prilikom dohvata predmeta:', err)
    });
  }
}
