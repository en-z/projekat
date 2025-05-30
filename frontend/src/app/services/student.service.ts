import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable({ providedIn: 'root' })
export class StudentService {
  private apiUrl = 'http://localhost:8080/api/studenti';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Student[]> {
    return this.http.get<Student[]>(this.apiUrl);
  }

  getById(id: number): Observable<Student> {
    return this.http.get<Student>(`${this.apiUrl}/${id}`);
  }

  search(params: any): Observable<Student[]> {
    let httpParams = new HttpParams();
    for (let key in params) {
      if (params[key] !== undefined && params[key] !== null && params[key] !== '') {
        httpParams = httpParams.set(key, params[key]);
      }
    }
    return this.http.get<Student[]>(`${this.apiUrl}/search`, { params: httpParams });
  }
}
