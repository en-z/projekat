import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Observable } from "rxjs";
import { Knjiga } from "../models/knjiga";
import { Izdate } from "../models/izdate";

@Injectable({
  providedIn:'root'
})
export class IzdateService extends BaseService<Izdate>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/biblioteka/izdate')
  }
  getByOsobaId(): Observable<Izdate[]> {
    return this.http.get<Izdate[]>(`${this.baseUrl}/izdaj/osoba`)
  }
  izdaj(id: number): Observable<Izdate> {
    return this.http.post<Izdate>(`${this.baseUrl}/izdaj/${id}`,{});
  }
  vrati(id: number): Observable<Izdate> {
    return this.http.post<Izdate>(`${this.baseUrl}/vrati/${id}`,{});
  }
}

