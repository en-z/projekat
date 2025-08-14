import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { OpstaObavjestenja } from "../models/opstaObavjestenja";

@Injectable({
  providedIn: 'root'
})
export class OpstaObavjestenjaService {
  private baseUrl = 'http://localhost:8080/api/osoblje/obavjestenja';

  constructor(private http: HttpClient) {}

  getTop10(fakultetID: number): Observable<OpstaObavjestenja[]> {
    return this.http.get<OpstaObavjestenja[]>(`${this.baseUrl}/${fakultetID}`);
  }

  getByFakultetMesecGodina(fakultetID: number, godina?: number, mesec?: number): Observable<OpstaObavjestenja[]> {
    let params = new HttpParams();
    if (godina != null) params = params.set('godina', godina.toString());
    if (mesec != null) params = params.set('mesec', mesec.toString());

    return this.http.get<OpstaObavjestenja[]>(`${this.baseUrl}/fakultet/${fakultetID}`, { params });
  }

  post(oba:OpstaObavjestenja): Observable<any>{
    return this.http.post('http://localhost:8080/api/osoblje/obavjestenja',oba);
  }
}

