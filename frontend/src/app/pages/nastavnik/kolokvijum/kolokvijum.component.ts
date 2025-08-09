import { Component, OnInit } from '@angular/core';
import { Student } from '../../../models/student';
import { Kolokvijum } from '../../../models/Kolokvijum';
import { KolokvijumService } from '../../../models/kolokvijum.service';
import { StudentService } from '../../../services/student.service';
import { KolokvijumRezultatService } from '../../../services/kolokvijumRezultat.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-kolokvijum',
  imports: [CommonModule,FormsModule],
  templateUrl: './kolokvijum.component.html',
  styleUrl: './kolokvijum.component.css'
})
export class KolokvijumComponent implements OnInit{
  predmetId = 1;      //dinamicki
  studentList:Student[]=[];
  kolokvijumi:Kolokvijum[]=[]
  selectedKolokvijumId: number | null = null;
  studentBodovi: { [studentId: number]: number } = {};
  constructor(private kolokvijumService:KolokvijumService,private studentService:StudentService,private kolokvijumRezultatService:KolokvijumRezultatService){}
  ngOnInit(): void {
    this.kolokvijumService.getByPredmet(this.predmetId).subscribe(data=>{
      this.kolokvijumi = data;
    })
  }
  onSelectKolokvijum(kolokvijumId: number) {
    this.selectedKolokvijumId = kolokvijumId;
    this.studentService.getByPredmetId(this.predmetId).subscribe(students => {
      this.studentList = students;
      this.studentBodovi = {};
      for (let student of students) {
        this.studentBodovi[student.id] = 0;
      }
    });
  }
  submitBodovi() {
  if (!this.selectedKolokvijumId) return;

  const payload = this.studentList.map(student => {
    const kolokvijum = this.kolokvijumi.find(k => k.id === this.selectedKolokvijumId);

    return {
      kolokvijumId: this.selectedKolokvijumId,
      predmetId: this.predmetId,
      naziv: kolokvijum?.naziv || '',//fallback
      studentId: student.id,
      bodovi: this.studentBodovi[student.id],
      datum:kolokvijum?.datum,
    };
  });
  this.kolokvijumRezultatService.postBodovi(payload).subscribe(response => {
    console.log('Rezultati uspešno poslati!', response);
  });
}
}
