import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { KnjigaService } from '../../../services/knjiga.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dodaj-knjigu',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './admin-dodaj-knjigu.component.html',
  styleUrl: './admin-dodaj-knjigu.component.css'
})
export class AdminDodajKnjiguComponent {
  knjigaForm!: FormGroup;

  constructor(private fb: FormBuilder,private knjigaService:KnjigaService) {}

  ngOnInit(): void {
    this.knjigaForm = this.fb.group({
      naziv: ['', Validators.required],
      kategorija: ['', Validators.required],
      opis: ['', Validators.required],
      autor: ['', Validators.required],
      kolicina: [0, [Validators.required, Validators.min(0)]],
    });
  }

  onSubmit() {
    if (this.knjigaForm.valid) {
      this.knjigaService.create(this.knjigaForm.value)
    } else {
      alert("Nije validna forma")
    }
  }
}
