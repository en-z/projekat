import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../models/predmet';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, ActivationEnd, Router } from '@angular/router';
import { PredmetService } from '../../services/predmet.service';
import { StudiskiService } from '../../services/studiski.service';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-admin-dodaj-predmet',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './admin-dodaj-predmet.component.html',
  styleUrl: './admin-dodaj-predmet.component.css'
})
export class AdminDodajPredmetComponent implements OnInit{
  predmetForm:FormGroup;
  listaPrograma:StudiskiProgram[]=[];
  id?:number;
  constructor (private programService:StudiskiService,private predmetService:PredmetService,private fb:FormBuilder,private route:ActivatedRoute,private router:Router){
    this.predmetForm = this.fb.group({
      id:[],
      naziv:[''],
      espb: [0, [Validators.required, Validators.min(1)]],
      semestar: [1, Validators.required],
      studiskiId: [null, Validators.required],
      dan: [null]
    })
  }
  ngOnInit(){
    this.programService.getAll().subscribe((data)=>{
      this.listaPrograma = data;
    })
    this.id = +this.route.snapshot.paramMap.get('id')!;
    if(this.id){
      this.predmetService.getById(this.id).subscribe((data)=>{
        this.predmetForm.patchValue(data)
      })
    }
  }
  onSubmit(){
    const predmet: Predmet = this.predmetForm.value;
    if (this.id) {
      this.predmetService.update(this.id!,predmet).subscribe(() => this.router.navigate(['/univerziteti']));
    } else {
      this.predmetService.create(predmet).subscribe(() => this.router.navigate(['/univerziti']));
    }
  }
}
