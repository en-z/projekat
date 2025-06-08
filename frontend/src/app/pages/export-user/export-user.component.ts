import { Component } from '@angular/core';
import { Student } from '../../models/student';
import { Nastavnik } from '../../models/nastavnik';
import { StudentService } from '../../services/student.service';
import { NastavnikService } from '../../services/nastavink.service';
import { exportToXml } from '../../extra/xml';
import { exportToPdf } from '../../extra/pdf';
@Component({
  selector: 'app-export-user',
  imports: [],
  templateUrl: './export-user.component.html',
  styleUrl: './export-user.component.css'
})
export class ExportUserComponent {
  studenti:Student[]=[]
  nastavnici:Nastavnik[]=[]
  loadedN=false;
  loadedS=false;
  constructor(private studentService:StudentService,private nastavnikService:NastavnikService){}
  exportNastavnikToPdf() {
    if (!this.loadedN) {
      this.getNastavnik(() => {
        exportToPdf(this.nastavnici, 'nastavnici.pdf', 'Spisak Nastavnika');
      });
    } else {
      exportToPdf(this.nastavnici, 'nastavnici.pdf', 'Spisak Nastavnika');
    }
  }

  exportNastavnikToXml() {
    if (!this.loadedN) {
      this.getNastavnik(() => {
        exportToXml(this.nastavnici, 'nastavnici', 'nastavnik', 'nastavnici.xml');
      });
    } else {
      exportToXml(this.nastavnici, 'nastavnici', 'nastavnik', 'nastavnici.xml');
    }
  }

  exportStudentToPdf() {
    if (!this.loadedS) {
      this.getStudente(() => {
        exportToPdf(this.studenti, 'studenti.pdf', 'Spisak Studenata');
      });
    } else {
      exportToPdf(this.studenti, 'studenti.pdf', 'Spisak Studenata');
    }
  }

  exportStudentToXml() {
    if (!this.loadedS) {
      this.getStudente(() => {
        exportToXml(this.studenti, 'studenti', 'student', 'studenti.xml');
      });
    } else {
      exportToXml(this.studenti, 'studenti', 'student', 'studenti.xml');
    }
  }

  getStudente(callback: () => void) {
    this.studentService.getAll().subscribe(data => {
      this.studenti = data;
      this.loadedS = true;
      callback();
    });
  }

  getNastavnik(callback: () => void) {
    this.nastavnikService.getAll().subscribe(data => {
      this.nastavnici = data;
      this.loadedN = true;
      callback();
    });
  }}
