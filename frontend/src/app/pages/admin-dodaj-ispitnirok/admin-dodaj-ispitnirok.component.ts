import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, ReactiveFormsModule } from '@angular/forms';
import { IspitniRokService } from '../../services/ispitniRok.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-admin-dodaj-ispitnirok',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './admin-dodaj-ispitnirok.component.html',
  styleUrl: './admin-dodaj-ispitnirok.component.css'
})
export class AdminDodajIspitnirokComponent implements OnInit {
  rokForm:FormGroup;
  id:number |null = null;
  constructor (private route:ActivatedRoute,private router:Router,private fb:FormBuilder,private rokService:IspitniRokService){
    this.rokForm = this.fb.group({
      naziv:[''],
      pocetak:[''],
      kraj:['']
    });
  }
  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if(this.id){
      this.rokService.getById(this.id).subscribe((data)=>{
        this.rokForm.patchValue(data)
      })
    }
  }
  onSubmit(){
    const rok = this.rokForm.value;
    if(this.id){
      this.rokService.update(this.id,rok).subscribe(()=>{
        this.router.navigate(['/ispitni-rokovi'])
      })
    }else{
      this.rokService.create(rok).subscribe(()=>{
        this.router.navigate(['/ispitni-rokovi'])
      })
    }
  }

}
