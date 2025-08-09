import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Osoblje } from '../../../models/osobljeRegDto';
import { OsobljeService } from '../../../services/osoblje.service';
import { FakultetService } from '../../../services/fakultet.service';
import { FakultetDTO } from '../../../models/fakultet';

@Component({
  selector: 'app-dodaj-osoblje',
  imports: [CommonModule,FormsModule],
  templateUrl: './dodaj-osoblje.component.html',
  styleUrl: './dodaj-osoblje.component.css'
})
export class DodajOsobljeComponent {
  osoblje: Osoblje = {
    ime: '',
    prezime: '',
    email: '',
    password: '',
    fakultetId: 0,
    roles: ['ROLE_OSOBLJE'],
  };

  fakulteti: FakultetDTO[]=[]

  constructor(
    private osobljeService: OsobljeService,
    private fakultetService:FakultetService
  ) {}

  ngOnInit(): void {
    this.fakultetService.getAll().subscribe(fakulteti => {
      this.fakulteti = fakulteti;
      if (fakulteti.length > 0) {
        this.osoblje.fakultetId = 0;// ovo moze da je error ako je prazno ali ostavljam za sad
      }
    });
  }

  kreiraj(): void {
    this.osobljeService.create(this.osoblje).subscribe({
    });
  }
}
