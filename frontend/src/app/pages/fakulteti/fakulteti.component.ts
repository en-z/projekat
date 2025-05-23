import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { FakultetDTO } from '../../models/fakultet';
@Component({
  selector: 'app-fakulteti',
  imports: [],
  templateUrl: './fakulteti.component.html',
  styleUrl: './fakulteti.component.css'
})
export class FakultetiComponent implements OnInit {

  constructor (private http:HttpClient,private router:Router){}

  ngOnInit(){
  }
}
