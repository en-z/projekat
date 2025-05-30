import { Component } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { Nastavnik } from '../../models/nastavnik';
import { UniverzitetService } from '../../services/univerzitet.service';
import { NastavnikService } from '../../services/nastavink.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-dodja-univerzite',
  imports: [CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './admin-dodja-univerzite.component.html',
  styleUrl: './admin-dodja-univerzite.component.css'
})
export class AdminDodjaUniverziteComponent {
  form:FormGroup;
  id?:number;
  nastavnici:Nastavnik[]=[];
  constructor(private fb:FormBuilder,private route:ActivatedRoute,private router:Router,private univerzitetService:UniverzitetService,private nastavnikService:NastavnikService){
  this.form = this.fb.group({
    naziv:[''],
    kontakt:[''],
    email:[''],
    opis:[''],
    rektorId:[''],
    ulica:[''],
    broj:[''],
    grad:[''],
    drzava:[''],
  });
  }
  ngOnInit(){
    this.nastavnikService.getAll().subscribe((data)=>{
      this.nastavnici = data;
    });
    console.log(this.nastavnici);
    this.id = +this.route.snapshot.paramMap.get('id')!;
    if(this.id){
      this.univerzitetService.getById(this.id).subscribe((data)=>{
        this.form.patchValue({
          naziv:data.naziv,
          kontakt: data.kontakt,
          email: data.email,
          opis: data.opis,
          rektorId: data.rektor.osoba_id,
          ulica: data.adresa.ulica,
          broj: data.adresa.broj,
          grad: data.adresa.grad,
          drzava: data.adresa.drzava,
        });
      });
    }
  }
  onSubmit(){
    const payload:Univerzitet= {
        id: undefined,
        naziv: this.form.value.naziv,
        kontakt: this.form.value.kontakt,
        email: this.form.value.email,
        opis: this.form.value.opis,
        rektor: {
            osoba_id: this.form.value.osoba_id,
            ime: '',
            prezime: ''
        },
        adresa: {
            id: null,
            ulica: this.form.value.ulica,
            broj: this.form.value.broj,
            grad: this.form.value.grad,
            drzava: this.form.value.drzava
        },
    };
    if(this.id){
      this.univerzitetService.update(this.id,payload).subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      });
    }else{
      this.univerzitetService.create(payload).subscribe(()=>{
        this.router.navigate(['/univerziteti'])
      })
    }
  }
}

