import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ZaUpis } from "../models/ZaUpis";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ZaUpisService{
    private apiUrl = 'http://localhost:8080/api/zaupis';
  constructor(private http:HttpClient){}

  getStudenteByFakultetId(fakultetId: number, page: number = 0, size: number = 25): Observable<any> {
    const params = new HttpParams()
      .set('page', page)
      .set('size', size);

    return this.http.get<any>(`${this.apiUrl}/${fakultetId}`, { params });
  }
}
