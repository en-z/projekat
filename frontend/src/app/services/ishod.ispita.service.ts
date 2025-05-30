import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IshodIspita } from '../models/ishod.ispita';


@Injectable({ providedIn: 'root' })
export class IshodIspitaService {
  private apiUrl = 'http://localhost:8080/api/ishodi';

  constructor(private http: HttpClient) {}

  getAll(): Observable<IshodIspita[]> {
    return this.http.get<IshodIspita[]>(this.apiUrl);
  }

  create(data: IshodIspita): Observable<IshodIspita> {
    return this.http.post<IshodIspita>(this.apiUrl, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getByPredmet(id: number): Observable<IshodIspita[]> {
    return this.http.get<IshodIspita[]>(`${this.apiUrl}/predmet/${id}`);
  }

  getByStudent(id: number): Observable<IshodIspita[]> {
    return this.http.get<IshodIspita[]>(`${this.apiUrl}/student/${id}`);
  }

  proveriStatusUnosaOcene(predmetId: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.apiUrl}/predmet/${predmetId}/unos-ocene-status`);
  }
}
