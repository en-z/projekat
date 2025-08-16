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
    super(http,'http://localhost:8080/api/nastavnik/nastavnici')
  }
    getNoIdFromToken(): Observable<Nastavnik> {
      return this.http.get<Nastavnik>(`http://localhost:8080/api/nastavnik/nastavnici`);
    }
  search(params: any): Observable<Nastavnik[]> {
    let httpParams = new HttpParams();
    for (let key in params) {
      if (params[key] !== undefined && params[key] !== null && params[key] !== '') {
        httpParams = httpParams.set(key, params[key]);
      }
    }
    return this.http.get<Nastavnik[]>(`http://localhost:8080/api/nastavnik/nastavnici/search`, { params: httpParams });
  }
}

