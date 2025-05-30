import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Termin } from '../models/termin';

@Injectable({ providedIn: 'root' })
export class TerminService {
  private apiUrl = 'http://localhost:8080/api/termini';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Termin[]> {
    return this.http.get<Termin[]>(this.apiUrl);
  }

  getById(id: number): Observable<Termin> {
    return this.http.get<Termin>(`${this.apiUrl}/${id}`);
  }

  getByPredmet(predmetId: number): Observable<Termin[]> {
    return this.http.get<Termin[]>(`${this.apiUrl}/predmet/${predmetId}`);
  }

  create(data: Termin): Observable<Termin> {
    return this.http.post<Termin>(this.apiUrl, data);
  }

  update(id: number, data: Termin): Observable<Termin> {
    return this.http.put<Termin>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
