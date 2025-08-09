import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Zahtjev } from "../models/zahtjev";

@Injectable({
  providedIn: 'root'
})
export class ZahtjevService {
  private baseUrl = '/api/zahtjevi';

  constructor(private http: HttpClient) {}

  search(searchData: ZahtjevSearch): Observable<Zahtjev[]> {
    return this.http.post<Zahtjev[]>(`${this.baseUrl}/search`, searchData);
  }
}
