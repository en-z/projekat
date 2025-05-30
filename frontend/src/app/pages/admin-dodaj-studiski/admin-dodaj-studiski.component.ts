import { Component } from '@angular/core';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { Nastavnik } from '../../models/nastavnik';
import { FakultetDTO } from '../../models/fakultet';
import { NastavnikService } from '../../services/nastavink.service';
import { FakultetService } from '../../services/fakultet.service';
import { StudiskiService } from '../../services/studiski.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
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
      naziv:[''],
      opis:[''],
      fakultet_id:[''],
      rukovodioc_id:[''],
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
          fakultet_id:data.fakultet.id,
          rukovodioc_id:data.rukovodioc.osoba_id,
        });
      });
    }
  }
  onSubmit(){
    const payload={
        naziv: this.form.value.naziv,
        opis:this.form.value.opis,
        fakultet_id:this.form.value.fakultet_id,
        rukovodioc_id:this.form.value.rukovodioc_id
    }
    if(this.id){
      this.http.put(`http://localhost:8080/api/program/${this.id}`,payload)
        .subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }else{
      this.http.post(`http://localhost:8080/api/program/`,payload)
        .subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }
  }
}
