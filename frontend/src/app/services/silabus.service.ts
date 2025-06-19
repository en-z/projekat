import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Silabus } from '../models/silabus';

@Injectable({ providedIn: 'root' })
export class SilabusService {
  private apiUrl = 'http://localhost:8080/api/nastavnik/silabusi';

  constructor(private http: HttpClient) {}

  getById(id: number): Observable<Silabus> {
    return this.http.get<Silabus>(`${this.apiUrl}/${id}`);
  }

  getByPredmetId(predmetId: number): Observable<Silabus> {
    return this.http.get<Silabus>(`${this.apiUrl}/predmet/${predmetId}`);
  }

  getAllByPredmetId(predmetId: number): Observable<Silabus[]> {
    return this.http.get<Silabus[]>(`${this.apiUrl}/predmeti/${predmetId}`);
  }
  create(data: Silabus): Observable<Silabus> {
    return this.http.post<Silabus>(this.apiUrl, data);
  }

  update(id: number, data: Silabus): Observable<Silabus> {
    return this.http.put<Silabus>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
