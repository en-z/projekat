import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { KolokvijumRezultat } from "../models/kolokvijumRezultat";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class KolokvijumRezultatService{
    baseUrl = "http://localhost:8080/api/nastavnik/kolokvijumi"
    constructor(private http:HttpClient){}
    postBodovi(rezultati: any[]) {
      return this.http.post(this.baseUrl, rezultati);
    }
    getRezultate(predmetId:number):Observable<KolokvijumRezultat[]>{ //za dobijanje rezultata studenta kada student zatrazi
      return this.http.get<KolokvijumRezultat[]>(`http://localhost:8080/api/student/studenti/rezultati/predmet/${predmetId}`)
    }
    getRezultateStudentaIPredmeta(predmetId:number,studentId:number):Observable<KolokvijumRezultat[]>{// za dobijanje rezultata studenta kad nastavnik zatrazi
      return this.http.get<KolokvijumRezultat[]>(`${this.baseUrl}/predmet/${predmetId}/student/${studentId}`)
    }
}

