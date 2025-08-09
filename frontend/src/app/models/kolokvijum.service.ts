import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Kolokvijum } from "./Kolokvijum";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class KolokvijumService{
  constructor(private http: HttpClient) {}

  getByProgram(programId: number): Observable<Kolokvijum[]> {
    return this.http.get<Kolokvijum[]>(`http://localhost:8080/api/osoblje/kolokvijum/program/${programId}`);
  }
  getByPredmet(predmetId: number): Observable<Kolokvijum[]> {
    return this.http.get<Kolokvijum[]>(`http://localhost:8080/api/osoblje/kolokvijum/predmet/${predmetId}`);
  }
  kreirajKolokvijume(lista: Kolokvijum[]): Observable<any> {
    return this.http.post('http://localhost:8080/api/osoblje/kolokvijum', lista);
  }
}

