import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { StudentService } from '../../services/student.service';
import { NastavnikService } from '../../services/nastavink.service';
import { ZavrsniRadService } from '../../services/zavrsni.rad.service';

@Component({
  selector: 'app-dodaj-zavrsnirad',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-zavrsnirad.component.html',
  styleUrl: './dodaj-zavrsnirad.component.css'
})
export class DodajZavrsniradComponent {
  form!: FormGroup;
  studentSearchForm!: FormGroup;
  nastavnikSearchForm!: FormGroup;
  studenti: any[] = [];
  nastavnici: any[] = [];
  showAdvancedSearch = false;
  constructor(
    private fb: FormBuilder,
    private studentService: StudentService,
    private nastavnikService: NastavnikService,
    private zavrsniRadService: ZavrsniRadService,
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      studentId: ['', Validators.required],
      nastavnikId: ['', Validators.required],
      naslov: ['', Validators.required],
      opis: ['', Validators.required],
      file: [null, Validators.required]
    });
    this.studentSearchForm = this.fb.group({
    brojIndeksa: [''],
    ime: [''],
    prezime: [''],
    godinaUpisa: [''],
    godinaStudija: [''],
    studiskiId: [''],
    prosekMin: [''],
    prosekMax: [''],
    esbpMin: [''],
    esbpMax: [''],
    aktivan: [''],
    ulica: [''],
    broj: [''],
    grad: [''],
    drzava: ['']
  });

  this.nastavnikSearchForm = this.fb.group({
    ime: [''],
    prezime: [''],
    email: ['']
  });
  }

  searchStudent() {
  const params: any = {};

  Object.entries(this.studentSearchForm.value).forEach(([key, value]) => {
    if (value !== null && value !== '' && value !== undefined) {
      params[key] = value;
    }
  });

  this.studentService.search(params).subscribe(data => {
    this.studenti = data;
  });
}
  searchNastavnik() {
   const params: any = {};

   Object.entries(this.nastavnikSearchForm.value).forEach(([key, value]) => {
    if (value !== null && value !== '' && value !== undefined) {
      params[key] = value;
    }
  });

//  this.nastavnikService.search(params).subscribe(data => {
 //   this.nastavnici = data;
  //});
}


  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.form.patchValue({ file: file });
    }
  }

  onSubmit() {
    if (this.form.invalid) return;

    const formData = new FormData();
    formData.append('studentId', this.form.get('studentId')?.value);
    formData.append('nastavnikId', this.form.get('nastavnikId')?.value);
    formData.append('naslov', this.form.get('naslov')?.value);
    formData.append('opis', this.form.get('opis')?.value);
    formData.append('file', this.form.get('file')?.value);

    this.zavrsniRadService.create(formData).subscribe({
      next: () => {
        this.form.reset();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }
}

