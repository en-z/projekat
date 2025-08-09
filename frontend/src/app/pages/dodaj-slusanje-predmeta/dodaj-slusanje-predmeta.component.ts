import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Student } from '../../models/student';
import { Page } from '../../models/Page';
import { Predmet } from '../../models/predmet';
import { HttpClient } from '@angular/common/http';
import { StudiskiService } from '../../services/studiski.service';
import { PredmetService } from '../../services/predmet.service';

@Component({
  selector: 'app-dodaj-slusanje-predmeta',
  imports: [FormsModule,CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-slusanje-predmeta.component.html',
  styleUrl: './dodaj-slusanje-predmeta.component.css'
})
export class DodajSlusanjePredmetaComponent {
  programi: StudiskiProgram[] = [];
  izabraniProgramId: number | null = null;

  studentiPage: Page<Student> | null = null;
  predmeti: Predmet[] = [];

  selectedPredmeti: { [studentId: number]: Set<number> } = {};
  expandedStudentId: number | null = null;

  currentPage = 0;

  constructor(private http: HttpClient,private studiskiService:StudiskiService,private predmetService:PredmetService) {}

  ngOnInit(): void {
    this.ucitajStudijskePrograme();
  }

  ucitajStudijskePrograme() {
    this.studiskiService.getByFakultet(1).subscribe(data=>{
      this.programi = data;
    });
  }

  izaberiProgram(id: number) {
    this.izabraniProgramId = id;
    this.ucitajPredmete();
    this.ucitajStudente();
  }

  ucitajPredmete() {
    this.predmetService.getByProgram(this.izabraniProgramId!)
      .subscribe(data => {
        this.predmeti = data;
      });
  }

  ucitajStudente() {
    this.http.get<Page<Student>>(`http://localhost:8080/api/student/studenti/pby?studiskiId=${this.izabraniProgramId}&page=${this.currentPage}&size=5`)
      .subscribe(data => {
        this.studentiPage = data;
        data.content.forEach(s => {
          if (!this.selectedPredmeti[s.id]) {
            this.selectedPredmeti[s.id] = new Set<number>();
          }
        });
      });
  }

  toggleRow(studentId: number) {
    this.expandedStudentId = this.expandedStudentId === studentId ? null : studentId;
  }

  togglePredmet(studentId: number, predmetId: number) {
    const selected = this.selectedPredmeti[studentId];
    if (selected.has(predmetId)) selected.delete(predmetId);
    else selected.add(predmetId);
  }

  sacuvaj() {
    const payload = Object.entries(this.selectedPredmeti)
      .map(([studentId, predmetSet]) => ({
        studentId: +studentId,
        predmetIds: Array.from(predmetSet)
      }));

    console.log(payload)
    this.http.post('http://localhost:8080/api/student/slusanja', payload).subscribe(() => {
    });
  }

  loadNext() {
    if (!this.studentiPage?.last) {
      this.currentPage++;
      this.ucitajStudente();
    }
  }

  loadPrevious() {
    if (!this.studentiPage?.first) {
      this.currentPage--;
      this.ucitajStudente();
    }
  }
}
