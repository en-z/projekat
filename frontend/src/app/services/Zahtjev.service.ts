import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Zahtjev } from "../models/zahtjev";
import { ZahtjevSearch } from "../models/ZahtjevSearch";

@Injectable({
  providedIn: 'root'
})
export class ZahtjevService {
  private baseUrl = 'http://localhost:8080/api/biblioteka/zahtjevi';

  constructor(private http: HttpClient) {}

  search(searchData: ZahtjevSearch): Observable<Zahtjev[]> {
    return this.http.post<any>(`${this.baseUrl}/search`, searchData);
  }
}
