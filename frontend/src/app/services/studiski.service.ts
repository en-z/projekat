import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { StudiskiProgram } from "../models/StudiskiProgram";
import { Observable } from "rxjs";

@Injectable({
  providedIn:'root'
})
export class StudiskiService extends BaseService<StudiskiProgram>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/program')
  }
  getByFakultet(id:number):Observable<StudiskiProgram[]>{
    const params = new HttpParams().set('fakultetId',id.toString());
    return this.http.get<StudiskiProgram[]>(`${this.baseUrl}/by-fakultet`,{params});
  }
}

