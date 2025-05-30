import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Nastavnik } from "../models/nastavnik";
import { Observable } from "rxjs";

@Injectable({
  providedIn:'root'
})
export class NastavnikService extends BaseService<Nastavnik>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/nastavnik')
  }
    getNoIdFromToken(): Observable<Nastavnik> {
      return this.http.get<Nastavnik>(`http://localhost:8080/api/nastavnik`);
    }
}

