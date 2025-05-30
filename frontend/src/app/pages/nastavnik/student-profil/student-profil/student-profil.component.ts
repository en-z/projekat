import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../../../../models/student';
import { StudentService } from '../../../../services/student.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-profil',
  imports: [CommonModule, FormsModule],
  templateUrl: './student-profil.component.html',
  styleUrls: ['./student-profil.component.css']
})
export class StudentProfilComponent implements OnInit {
  student?: Student;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    this.studentService.getById(+id).subscribe({
      next: (data) => {
        this.student = data;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        alert('Greska pri ucitavanju');
      }
    });
  }
}
