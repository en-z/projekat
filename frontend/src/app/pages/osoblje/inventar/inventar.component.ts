import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Inventar } from '../../../models/inventar';
import { FormsModule, NgModel } from '@angular/forms';
import { InventarService } from '../../../services/inverntar.service';

@Component({
  selector: 'app-inventar',
  imports: [CommonModule,FormsModule],
  templateUrl: './inventar.component.html',
  styleUrl: './inventar.component.css'
})
export class InventarComponent implements OnInit{
  inventar: Inventar[] = [];
  narudzbe: { [id: number]: number } = {};
  noviProizvod: { naziv: string; opis: string } = { naziv: '', opis: '' };
  fakultetId = 1;

  constructor(private inventarService: InventarService) {}

  ngOnInit() {
    this.ucitajInventar();
  }

  ucitajInventar() {
    this.inventarService.getAll(this.fakultetId).subscribe(data => {
      this.inventar = data;
    });
  }

  naruci() {
    const naruceni = this.inventar
      .filter(item => this.narudzbe[item.id!])
      .map(item => ({
        ...item,
        kolicina: (item.kolicina || 0) + this.narudzbe[item.id!]
      }));

    this.inventarService.saveAll(naruceni).subscribe(() => {
      this.narudzbe = {};
      this.ucitajInventar();
    });
  }

  dodajNovi() {
    if (this.noviProizvod.naziv.trim()) {
      const novi= {
        id:null,
        naziv: this.noviProizvod.naziv,
        opis: this.noviProizvod.opis,
        fakultetId: this.fakultetId,
        kolicina: 0
      };

      this.inventarService.create(novi).subscribe(() => {
        this.noviProizvod = { naziv: '', opis: '' };
        this.ucitajInventar();
      });
    }
  }
}
