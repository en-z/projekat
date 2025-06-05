import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Predmet } from "../models/predmet";
import { Observable } from "rxjs";

@Injectable({
  providedIn:'root'
})
export class PredmetService extends BaseService<Predmet>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/predmeti')
  }

  getByProgram(id:number):Observable<Predmet[]>{
    const params = new HttpParams().set('programId',id.toString());
    return this.http.get<Predmet[]>(`${this.baseUrl}/by-program`,{params});
  }
}
