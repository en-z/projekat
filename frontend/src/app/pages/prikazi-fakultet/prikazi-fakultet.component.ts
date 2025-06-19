import { Component, OnInit } from '@angular/core';
import { FakultetDTO } from '../../models/fakultet';
import { FakultetService } from '../../services/fakultet.service';
import { StudiskiProgram } from '../../models/StudiskiProgram';
import { StudiskiService } from '../../services/studiski.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-prikazi-fakultet',
  imports: [CommonModule],
  templateUrl: './prikazi-fakultet.component.html',
  styleUrl: './prikazi-fakultet.component.css'
})
export class PrikaziFakultetComponent implements OnInit{
  fakultet:FakultetDTO|null =null;
  id:number|null=null
  idPrograma:number|null=null;
  programe:StudiskiProgram[]=[]
  constructor(public authService:AuthService,private fakultetService:FakultetService,private programService:StudiskiService,public router:Router,private route:ActivatedRoute){}
  ngOnInit(): void {
    this.id=Number(this.route.snapshot.paramMap.get('id'))
    this.fakultetService.getById(this.id).subscribe(data=>{this.fakultet=data
                                                    this.loadPrograme();
                                                    console.log(data)
    })
  }
  loadPrograme(){
    this.programService.getByFakultet(this.id!).subscribe(data=>{this.programe=data
                                                          console.log(data)})
  }
  delete(id:number){
    if(confirm("brisanje programa")){
      this.programService.delete(id).subscribe(()=>{
       this.programe = this.programe.filter(u=>u.id !==id)
      })
    }
  }
}
