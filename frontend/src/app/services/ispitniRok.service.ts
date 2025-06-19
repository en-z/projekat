import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { IspitniRok } from "../models/ispitniRok";
import { Observable } from "rxjs";

@Injectable({
  providedIn:'root'
})
export class IspitniRokService extends BaseService<IspitniRok>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/student/ispitniRok')
  }
  getAktivne(): Observable<IspitniRok[]> {
    return this.http.get<IspitniRok[]>(`${this.baseUrl}/aktivni`);
  }
}
