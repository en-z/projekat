import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormsModule } from '@angular/forms';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Predmet } from '../../models/predmet';
import { StudentService } from '../../services/student.service';
import { StudiskiService } from '../../services/studiski.service';
import { PredmetService } from '../../services/predmet.service';
import { Nastavnik } from '../../models/nastavnik';
import { Angazovanje } from '../../models/angazovanje';
import { NastavnikService } from '../../services/nastavink.service';

@Component({
  selector: 'app-dodaj-angazovanja',
  imports: [CommonModule,FormsModule],
  templateUrl: './dodaj-angazovanja.component.html',
  styleUrl: './dodaj-angazovanja.component.css'
})
export class DodajAngazovanjaComponent {
}
