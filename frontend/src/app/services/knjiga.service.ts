import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Observable } from "rxjs";
import { Knjiga } from "../models/knjiga";

@Injectable({
  providedIn:'root'
})
export class KnjigaService extends BaseService<Knjiga>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/biblioteka/knjiga')
  }
  getByKategorija(kategorija: string): Observable<Knjiga[]> {
    return this.http.get<Knjiga[]>(`${this.baseUrl}/kategorija/${kategorija}`);
  }
}

