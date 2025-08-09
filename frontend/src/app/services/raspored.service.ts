import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Raspored } from "../models/raspored";

@Injectable({ providedIn: 'root' })
export class RasporedService {
  constructor(private http: HttpClient) {}

  kreirajRaspored(lista: Raspored[]): Observable<any> {
    return this.http.post(`http://localhost:8080/api/osoblje/raspored`, lista);
  }
    getByProgram(programId: number): Observable<Raspored[]> {
      return this.http.get<Raspored[]>(`http://localhost:8080/api/osoblje/raspored/by-program/${programId}`);
    }
}

