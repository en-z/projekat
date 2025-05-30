import { Component } from '@angular/core';
import { Student } from '../../../../models/student';
import { StudentService } from '../../../../services/student.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-pretraga-studenata',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './pretraga-studenata.component.html',
  styleUrls: ['./pretraga-studenata.component.css']
})
export class PretragaStudenataComponent {
  searchParams: any = {};
  studenti: Student[] = [];
  loading = false;

  constructor(private studentService: StudentService) {}

  pretrazi() {
    this.loading = true;
    this.studentService.search(this.searchParams).subscribe({
      next: (data) => {
        this.studenti = data;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        alert('Greška prilikom pretrage');
      }
    });
  }
}
