import { Component, OnInit } from '@angular/core';
import { Student } from '../../models/student';
import { StudentService } from '../../services/student.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-studenti',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './studenti.component.html',
  styleUrl: './studenti.component.css'
})
export class StudentiComponenti implements OnInit {
  searchForm!:FormGroup;
  list:Student[]=[];
  filtirano:Student[]=[];
  constructor(private fb:FormBuilder,private studentiService:StudentService){}
  ngOnInit(): void {
    this.ucitajStudente()
    this.searchForm = this.fb.group({
      ime: [''],
      prezime: [''],
      godinaUpisa: [''],
      email: [''],
      prosecnaOcena: [''],
      osvojeniESPB: [''],
      studiskiId: ['']
    });
    this.searchForm.valueChanges.subscribe(() => {
      this.onSearch();
    });
  }
  ucitajStudente(){
    this.studentiService.getAll().subscribe(data=>this.list = data)
  }
  onSearch() {
    const criteria = this.searchForm.value;

    this.filtirano= this.list.filter(student => {
      return Object.keys(criteria).every(key => {
        const fieldValue = (student as any)[key];
        const filterValue = criteria[key];

        if (filterValue === '' || filterValue === null || filterValue === undefined) {
          return true; // preskoči prazne
        }

        if (typeof fieldValue === 'string') {
          return fieldValue.toLowerCase().includes(filterValue.toLowerCase());
        }

        if (typeof fieldValue === 'number') {
          return fieldValue === +filterValue;
        }

        return false;
      });
    });
  }

}
