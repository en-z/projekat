import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Angazovanje } from "../models/angazovanje";
import { AngazovanjeRes } from "../models/angazovanjeRes";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AngazovanjaService{
   private baseUrl = 'http://localhost:8080/api/student/slusanja';

  constructor(private http: HttpClient) {}

  postAngazovanja(userId:number,predmetIds:number[]):Observable<any>{
    return this.http.post(this.baseUrl,{userId,predmetIds})
  }
}
