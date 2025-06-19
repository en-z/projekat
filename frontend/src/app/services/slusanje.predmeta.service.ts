import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SlusanjePredmeta } from '../models/slusanje.predmeta';

@Injectable({ providedIn: 'root' })
export class SlusanjePredmetaService {
  private apiUrl = 'http://localhost:8080/api/student/slusanja';

  constructor(private http: HttpClient) {}

  getByPredmet(predmetId: number): Observable<SlusanjePredmeta[]> {
    return this.http.get<SlusanjePredmeta[]>(`${this.apiUrl}/predmet/${predmetId}`);
  }

  getByStudent(studentId: number): Observable<SlusanjePredmeta[]> {
    return this.http.get<SlusanjePredmeta[]>(`${this.apiUrl}/student/${studentId}`);
  }
}
