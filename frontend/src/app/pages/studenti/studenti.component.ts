import { Component, OnInit } from '@angular/core';
import { Student } from '../../models/student';
import { StudentService } from '../../services/student.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { exportToXml } from '../../extra/xml';
import { exportToPdf } from '../../extra/pdf';

@Component({
  selector: 'app-studenti',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './studenti.component.html',
  styleUrl: './studenti.component.css'
})
export class StudentiComponenti implements OnInit {
  studenti: Student[] = [];
  filtrirani: Student[] = [];
  searchForm!: FormGroup;

  constructor(private fb: FormBuilder, private studentService: StudentService) {}

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      ime: [''],
      prezime: [''],
      godinaUpisa: [''],
      email: [''],
      prosecnaOcena: [''],
      osvojeniESPB: [''],
      studiskiId: ['']
    });

    this.studentService.getAll().subscribe(data => {
      this.studenti = data;
      this.filtrirani = data;
    });

    this.searchForm.valueChanges.subscribe(() => this.onSearch());
  }

  onSearch(): void {
    const { ime, prezime, godinaUpisa, email, prosecnaOcena, osvojeniESPB, studiskiId } = this.searchForm.value;

    this.filtrirani = this.studenti.filter(s =>
      (!ime || s.ime.toLowerCase().includes(ime.toLowerCase())) &&
      (!prezime || s.prezime.toLowerCase().includes(prezime.toLowerCase())) &&
      (!godinaUpisa || s.godinaUpisa.toString().includes(godinaUpisa)) &&
      (!email || s.email.toLowerCase().includes(email.toLowerCase())) &&
      (!prosecnaOcena || s.prosecnaOcena.toString().includes(prosecnaOcena)) &&
      (!osvojeniESPB || s.osvojeniESPB.toString().includes(osvojeniESPB)) &&
      (!studiskiId || s.studiskiId.toString().includes(studiskiId))
    );
  }
    xmlExport(){
    if(this.filtrirani.length>0){
      exportToXml(this.filtrirani,"studenti","student","student.xml")
    }else{
      exportToXml(this.studenti,"studenti","student","student.xml")
    }
  }
  pdfExport(){
    if(this.filtrirani.length>0){
      exportToPdf(this.filtrirani,"Studenti.pdf","Spisak studenata")
    }else{
      exportToPdf(this.studenti,"Studenti.pdf","Spisak studenata")
    }
  }

}
