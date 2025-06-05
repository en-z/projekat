import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-form-predmet',
  imports: [CommonModule],
  templateUrl: './form-predmet.component.html',
  styleUrl: './form-predmet.component.css'
})
export class FormPredmetComponent {
  id:number|null=null;
  formPredmet:FormGroup;
  constructor(private fb:FormBuilder){
    this.formPredmet = this.fb.group({
      id
    })
  }
}
