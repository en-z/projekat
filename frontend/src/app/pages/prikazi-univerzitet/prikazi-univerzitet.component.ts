import { Component, OnInit } from '@angular/core';
import { Univerzitet } from '../../models/univerzitet';
import { UniverzitetService } from '../../services/univerzitet.service';
import { FakultetDTO } from '../../models/fakultet';
import { ActivatedRoute, Router } from '@angular/router';
import { FakultetService } from '../../services/fakultet.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-prikazi-univerzitet',
  imports: [CommonModule,ReactiveFormsModule],
  templateUrl: './prikazi-univerzitet.component.html',
  styleUrl: './prikazi-univerzitet.component.css'
})
export class PrikaziUniverzitetComponent implements OnInit {
  univerzitet:Univerzitet|null=null;
  fakulteti:FakultetDTO[]=[]
  id:number|null=null;
  constructor(public authService:AuthService,private route:ActivatedRoute,public router:Router,private univerzitetService:UniverzitetService,private fakService:FakultetService){
  }
  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'))
    this.univerzitetService.getById(this.id).subscribe(data=>{this.univerzitet = data,
                                                       console.log(data)
      this.loadFakultete()
    })
  }
  loadFakultete(){
    this.fakService.getByUniverzitetId(this.id!).subscribe(data=>this.fakulteti = data)
  }
  delete(id:number){
      this.fakService.delete(id).subscribe(()=>{
       this.fakulteti= this.fakulteti.filter(u=>u.id !==id)
      })
  }
}
