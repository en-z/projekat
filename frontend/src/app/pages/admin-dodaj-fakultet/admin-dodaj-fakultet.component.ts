import { Component } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { FakultetDTO } from '../../models/fakultet';
import { Adresa } from '../../models/adresa';
import { FakultetService } from '../../services/fakultet.service';
import { UniverzitetService } from '../../services/univerzitet.service';
import { NastavnikService } from '../../services/nastavink.service';
import { Nastavnik } from '../../models/nastavnik';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-admin-dodaj-fakultet',
  imports: [CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './admin-dodaj-fakultet.component.html',
  styleUrl: './admin-dodaj-fakultet.component.css'
})
export class AdminDodajFakultetComponent {
  nastavnici:Nastavnik[]=[]
  univerziteti:Univerzitet[]=[]
  id:number|null = null;
  form:FormGroup;
  constructor(private fb:FormBuilder,private route:ActivatedRoute,private router:Router,private fakultetService:FakultetService,private nastavnikService:NastavnikService,private UniverzitetService:UniverzitetService,private http:HttpClient){
    this.form = this.fb.group({
      naziv:[''],
      kontakt:[''],
      email:[''],
      opis:[''],
      dekan_id:[''],
      univerzitet_id:[''],
      ulica:[''],
      broj:[''],
      grad:[''],
      drzava:[''],
    });
  }
  ngOnInit(){
    this.nastavnikService.getAll().subscribe((data)=>{
      this.nastavnici = data;
    })
    this.UniverzitetService.getAll().subscribe((data)=>{
      this.univerziteti= data;
    })
    this.id = +this.route.snapshot.paramMap.get('id')!;
    if(this.id){
      this.fakultetService.getById(this.id).subscribe((data)=>{
        this.form.patchValue({
          naziv:data.naziv,
          kontakt: data.kontakt,
          email: data.email,
          opis: data.opis,
          dekan_id: data.dekan.osoba_id,
          univerzitet_id: data.univerzitet.id,
          ulica: data.adresa.ulica,
          broj: data.adresa.broj,
          grad: data.adresa.grad,
          drzava: data.adresa.drzava,
        });
      });
    }
  }
  onSubmit(){
    const payload={
        naziv: this.form.value.naziv,
        kontakt: this.form.value.kontakt,
        email: this.form.value.email,
        opis: this.form.value.opis,
        rektor_id:this.form.value.rektor_id,
        univerziteti_id:this.form.value.univerzitet_id,
        adresa: {
            id: null,
            ulica: this.form.value.ulica,
            broj: this.form.value.broj,
            grad: this.form.value.grad,
            drzava: this.form.value.drzava
        },
    }
    if(this.id){
      this.http.put(`http://localhost:8080/api/fakultet/${this.id}`,payload)
        .subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }else{
      this.http.post(`http://localhost:8080/api/fakultet/`,payload)
        .subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }
  }
}
