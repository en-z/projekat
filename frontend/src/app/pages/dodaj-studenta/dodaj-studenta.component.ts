import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-dodaj-studenta',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './dodaj-studenta.component.html',
  styleUrl: './dodaj-studenta.component.css'
})
export class DodajStudentaComponent {
  form!: FormGroup;
  users: any[] = [];
  studijskiProgrami: any[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      userId: ['', Validators.required],
      studiskiId: ['', Validators.required],
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      ulica: ['', Validators.required],
      broj: ['', Validators.required],
      grad: ['', Validators.required],
      drzava: ['', Validators.required],
    });

    this.http.get<any[]>('http://localhost:8080/api/user/zaUpis').subscribe(data => this.users = data);
    this.http.get<any[]>('http://localhost:8080/api/admin/studijski-programi').subscribe(data => this.studijskiProgrami = data);
  }

onUserSelect(event: Event) {
  const selectElement = event.target as HTMLSelectElement;
  const userId = selectElement.value;

  if (!userId) return;

  const selected = this.users.find(u => u.id === +userId);
  if (selected) {
    this.form.patchValue({
      ime: selected.ime,
      prezime: selected.prezime
    });
  }
}

  onSubmit(): void {
    if (this.form.invalid) return;

    this.http.post('/api/studenti/student', this.form.value).subscribe({
      next: () => alert('Student uspešno dodat!'),
      error: () => alert('Greška pri dodavanju studenta!')
    });
  }
}
