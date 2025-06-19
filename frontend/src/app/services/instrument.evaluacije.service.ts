import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InstrumentEvaluacije } from '../models/instrument.evaluacije';


@Injectable({ providedIn: 'root' })
export class InstrumentEvaluacijeService {
  private apiUrl = 'http://localhost:8080/api/nastavnik/instrumenti';

  constructor(private http: HttpClient) {}

  getAll(): Observable<InstrumentEvaluacije[]> {
    return this.http.get<InstrumentEvaluacije[]>(this.apiUrl);
  }

  getById(id: number): Observable<InstrumentEvaluacije> {
    return this.http.get<InstrumentEvaluacije>(`${this.apiUrl}/${id}`);
  }

  getByPredmetId(predmetId: number): Observable<InstrumentEvaluacije[]> {
    return this.http.get<InstrumentEvaluacije[]>(`${this.apiUrl}/predmet/${predmetId}`);
  }


  create(data: InstrumentEvaluacije): Observable<InstrumentEvaluacije> {
    return this.http.post<InstrumentEvaluacije>(this.apiUrl, data);
  }

  update(id: number, data: InstrumentEvaluacije): Observable<InstrumentEvaluacije> {
    return this.http.put<InstrumentEvaluacije>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
