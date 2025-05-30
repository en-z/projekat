import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { IshodIspita } from "../models/ishodIspita";
import { FakultetDTO } from "../models/fakultet";
import { Observable } from "rxjs";

@Injectable({
  providedIn:'root'
})
export class FakultetService extends BaseService<FakultetDTO>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/fakultet')
  }
  getByUniverzitetId(univerzitetId: number): Observable<FakultetDTO[]> {
    const params = new HttpParams().set('univerzitetId', univerzitetId.toString());
    return this.http.get<FakultetDTO[]>(`${this.baseUrl}/by-univerzitet`, { params });
  }
}
