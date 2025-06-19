import { Component, OnInit } from '@angular/core';
import { NastavnikService } from '../../services/nastavnik.service';
import { Nastavnik } from '../../models/nastavnik';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { exportToPdf } from '../../extra/pdf';
import { exportToXml } from '../../extra/xml';

@Component({
  selector: 'app-nastavnici',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './nastavnici.component.html',
  styleUrl: './nastavnici.component.css'
})
export class NastavniciComponent implements OnInit{
  nastavnici: Nastavnik[] = [];
  filtrirani: Nastavnik[] = [];
  searchForm: FormGroup;

  constructor(private nastavnikService: NastavnikService, private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      ime: [''],
      prezime: [''],
      biografija: [''],
      status: ['']
    });
  }

  ngOnInit(): void {
    this.nastavnikService.getAll().subscribe(data => {
      this.nastavnici = data;
      this.filtrirani = [];
    });

    this.searchForm.valueChanges.subscribe(() => this.onSearch());
  }

  onSearch(): void {
    const { ime, prezime, biografija, status } = this.searchForm.value;

    const anyFilter = ime || prezime || biografija || status;

    if (!anyFilter) {
      this.filtrirani = [];
      return;
    }

    this.filtrirani = this.nastavnici.filter(n =>
      (!ime || n.ime.toLowerCase().includes(ime.toLowerCase())) &&
      (!prezime || n.prezime.toLowerCase().includes(prezime.toLowerCase())) &&
      (!biografija || n.biografija.toLowerCase().includes(biografija.toLowerCase())) &&
      (!status || n.status.toLowerCase().includes(status.toLowerCase()))
    );
  }
  xmlExport(){
    if(this.filtrirani.length>0){
      exportToXml(this.filtrirani,"nastavnici","nastavnik","nastavnici.xml")
    }else{
      exportToXml(this.nastavnici,"nastavnici","nastavnik","nastavnici.xml")
    }
  }
  pdfExport(){
    if(this.filtrirani.length>0){
      exportToPdf(this.filtrirani,"Nastavnici.pdf","Spisak nastavnika")
    }else{
      exportToPdf(this.nastavnici,"Nastavnici.pdf","Spisak nastavnika")
    }
  }
}
