import { Component } from '@angular/core';
import { Student } from '../../models/student';
import { Nastavnik } from '../../models/nastavnik';
import { StudentService } from '../../services/student.service';
import { NastavnikService } from '../../services/nastavink.service';
import { exportToXml } from '../../extra/xml';
import { exportToPdf } from '../../extra/pdf';
import { IshodIspitaService } from '../../services/ishod.ispita.service';
import { IshodIspita } from '../../models/ishod.ispita';
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
  constructor(private studentService:StudentService,private nastavnikService:NastavnikService,private ishodService:IshodIspitaService){}
  exportNastavnikToPdf() {
    const sanitized = this.nastavnici.map(n => ({
      id: n.id,
      ime: n.ime,
      prezime: n.prezime,
      biografija: n.biografija,
      status: n.status,
      adresa:n.adresa
    }));

    exportToPdf(sanitized, 'nastavnici.pdf', 'Spisak Nastavnika');
  }

  exportNastavnikToXml() {
    if (!this.loadedN) {
      this.getNastavnik(() => {
        const sanitized = this.nastavnici.map(n => ({
          id: n.id,
          ime: n.ime,
          prezime: n.prezime,
          biografija: n.biografija,
          status: n.status,
          adresa:n.adresa
        }));

        exportToXml(sanitized, 'nastavnici', 'nastavnik', 'nastavnici.xml');
      });
    } else {
        const sanitized = this.nastavnici.map(n => ({
          id: n.id,
          ime: n.ime,
          prezime: n.prezime,
          biografija: n.biografija,
          status: n.status,
          adresa:n.adresa
        }));
      exportToXml(sanitized, 'nastavnici', 'nastavnik', 'nastavnici.xml');
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
      console.log(data)
      this.loadedN = true;
      callback();
    });
  }

  parseDateArrayToDate(dateArray: number[]): Date | null {
  if (!Array.isArray(dateArray) || dateArray.length < 6) return null;

  const [year, month, day, hour, minute, second] = dateArray;
  return new Date(year, month - 1, day, hour, minute, second);
}

exportIshode(){
this.ishodService.getAll().subscribe(data => {
  const lista = data.map(item => {
    if (Array.isArray(item.datumUnosa)) {
      const d = this.parseDateArrayToDate(item.datumUnosa);
      if (d) item.datumUnosa = d;
    } else if (typeof item.datumUnosa === 'string') {
      item.datumUnosa = new Date(item.datumUnosa);
    }
    // datumUnosa should now be a Date object

    return item;
  });

  // Now, before exporting, convert Date to string, e.g.:
  const exportList = lista.map(item => ({
    ...item,
    datumUnosa: item.datumUnosa instanceof Date ? item.datumUnosa.toISOString() : ''
  }));

  exportToXml(exportList, 'ishodi', 'ishod', 'dsfa.xml');
});
}
}
