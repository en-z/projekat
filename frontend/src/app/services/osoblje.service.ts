import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Osoblje } from "../models/osobljeRegDto";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class OsobljeService{
  constructor (private http:HttpClient){}
  create(osoblje:Osoblje):Observable<any>{
    return this.http.post('http://localhost:8080/api/osoblje/raspored',osoblje)
  }
}
