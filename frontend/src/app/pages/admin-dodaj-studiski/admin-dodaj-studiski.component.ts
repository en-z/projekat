import { Component } from '@angular/core';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Nastavnik } from '../../models/nastavnik';
import { FakultetDTO } from '../../models/fakultet';
import { NastavnikService } from '../../services/nastavink.service';
import { FakultetService } from '../../services/fakultet.service';
import { StudiskiService } from '../../services/studiski.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-admin-dodaj-studiski',
  imports: [CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './admin-dodaj-studiski.component.html',
  styleUrl: './admin-dodaj-studiski.component.css'
})
export class AdminDodajStudiskiComponent {
  fakulteti:FakultetDTO[]=[]
  nastavnici:Nastavnik[]=[]
  id:number|null =null;
  form:FormGroup;
  constructor(
    private nastavnikService:NastavnikService,
    private fakultetService:FakultetService,
    private programService:StudiskiService,
    private route:ActivatedRoute,
    private router:Router,
    private fb:FormBuilder,
    private http:HttpClient
  ){
    this.form = this.fb.group({
      naziv:['',Validators.required],
      opis:['',Validators.required],
      fakultet_id:['',Validators.required],
      rukovodioc:['',Validators.required],
    })
  }
  ngOnInit(){
    this.nastavnikService.getAll().subscribe((data)=>{
      this.nastavnici=data
    })
    this.fakultetService.getAll().subscribe((data)=>{
      this.fakulteti=data
    })
    this.id = +this.route.snapshot.paramMap.get('id')!;
    if(this.id){
      this.programService.getById(this.id).subscribe((data)=>{
        this.form.patchValue({
          naziv:data.naziv,
          opis:data.opis,
          fakultetId:data.fakultetId,
          rukovodioc:data.rukovodioc.id,
        });
      });
    }
  }
  onSubmit(){
    const payload:StudiskiProgram={
        naziv: this.form.value.naziv,
        opis: this.form.value.opis,
        rukovodioc: {
            id: this.form.value.rukovodioc,
            ime: '',
            prezime: '',
            biografija: '',
            satus: '',
            angazovanja: [],
            adresa: {
              id: null,
              ulica: this.form.value.ulica,
              broj: this.form.value.broj,
              grad: this.form.value.grad,
              drzava: this.form.value.drzava
            },
        },
        fakultetId: this.form.value.fakultet_id,
        id: null
    }
    if(this.id){
        this.programService.update(this.id,payload)
        .subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }else{
      this.programService.create(payload)
        .subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }
  }
}
