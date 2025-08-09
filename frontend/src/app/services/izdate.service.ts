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
    return this.http.get<Izdate[]>(`${this.baseUrl}/user`)
  }
  izdaj(dto:Izdate,id:number): Observable<Izdate> {
    return this.http.post<any>(`${this.baseUrl}/izdaj/${id}`,{dto});
  }
  zahtjev(knjigaId: number): Observable<Izdate> {
    return this.http.post<any>(`http://localhost:8080/api/biblioteka/zahtjevi`,{knjigaId});
  }
  vrati(id: number): Observable<Izdate> {
    return this.http.post<Izdate>(`${this.baseUrl}/vrati/${id}`,{});
  }
  search(asd:any):Observable<Izdate[]>{
    return this.http.post<any>(`${this.baseUrl}/search`,{asd});
  }
}

