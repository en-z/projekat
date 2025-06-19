import { Component, OnInit } from '@angular/core';
import { Izdate } from '../../models/izdate';
import { IzdateService } from '../../services/izdate.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vrati',
  imports: [CommonModule],
  templateUrl: './vrati.component.html',
  styleUrl: './vrati.component.css'
})
export class VratiComponent implements OnInit{
  izdate:Izdate[]=[]
  constructor(private izdateService:IzdateService){}
  ngOnInit(){
    this.izdateService.getByOsobaId().subscribe(data=>{
      this.izdate = data
    })
  }
  vrati(id:number){
    this.izdateService.vrati(id).subscribe({
    next: () => {
      this.izdateService.getByOsobaId().subscribe(data => {
        this.izdate = data;
      })
      ;
    },
    error: err => {
      console.error('Greška pri vraćanju knjige:', err);
    }
  });
  }
}
