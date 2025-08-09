import { Component, OnInit } from '@angular/core';
import { Izdate } from '../../models/izdate';
import { IzdateService } from '../../services/izdate.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-vrati',
  imports: [CommonModule,ReactiveFormsModule,FormsModule],
  templateUrl: './vrati.component.html',
  styleUrl: './vrati.component.css'
})
export class VratiComponent implements OnInit{
   searchForm: FormGroup;
  izdate: Izdate[] = [];

  constructor(private izdateService: IzdateService, private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      userIdMin: [''],
      userIdMax: [''],
      knjigaNazivContains: [''],
      knjigaKategorijaContains: [''],
      knjigaOpisContains: [''],
      knjigaGodinaIzdavanjaMin: [''],
      knjigaGodinaIzdavanjaMax: [''],
      knjigaAutorContains: [''],
      knjigaKolicinaMin: [''],
      knjigaKolicinaMax: [''],
      trajna: [null]
    });
  }

  ngOnInit(): void {
    this.pretrazi();
  }

  pretrazi(): void {
    const criteria = this.searchForm.value;

    Object.keys(criteria).forEach(key => {
      if (criteria[key] === '') {
        criteria[key] = null;
      }
    });

    this.izdateService.search(criteria).subscribe(res => {
      this.izdate = res;
    });
  }

  vrati(id: number): void {
    this.izdateService.vrati(id).subscribe(() => {
    });
  }
}
