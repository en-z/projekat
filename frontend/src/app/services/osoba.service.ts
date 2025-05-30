import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Nastavnik } from "../models/nastavnik";
import { Observable } from "rxjs";
import { Osoba } from "../models/osoba";

@Injectable({
  providedIn:'root'
})
export class OsobaService extends BaseService<Osoba>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/osoba')
  }
    getNoIdFromToken(): Observable<Nastavnik> {
      return this.http.get<Nastavnik>(`http://localhost:8080/api/osoba`);
    }
}

