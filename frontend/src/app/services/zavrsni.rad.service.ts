import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ZavrsniRadService {
  private apiUrl = 'http://localhost:8080/api/zavrsniRadovi';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getByMentor(nastavnikId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/mentor/${nastavnikId}`);
  }
}
