import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Obavestenje } from '../models/obavestenje';

@Injectable({ providedIn: 'root' })
export class ObavestenjeService {
  private apiUrl = 'http://localhost:8080/api/nastavnik/obavestenja';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Obavestenje[]> {
    return this.http.get<Obavestenje[]>(this.apiUrl);
  }

  getById(id: number): Observable<Obavestenje> {
    return this.http.get<Obavestenje>(`${this.apiUrl}/${id}`);
  }

  getByPredmet(predmetId: number): Observable<Obavestenje[]> {
    return this.http.get<Obavestenje[]>(`${this.apiUrl}/predmet/${predmetId}`);
  }


  create(data: Obavestenje): Observable<Obavestenje> {
    return this.http.post<Obavestenje>(this.apiUrl, data);
  }

  update(id: number, data: Obavestenje): Observable<Obavestenje> {
    return this.http.put<Obavestenje>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
