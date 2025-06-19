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
    return this.http.get(`http://localhost:8080/api/nastavnik/zavrsni/mentor/${nastavnikId}`);
  }

  create(formData: FormData): Observable<any> {
    return this.http.post(`http://localhost:8080/api/nastavnik/zavrsni`, formData, { responseType: 'text' });
  }

  downloadFile(zavrsniRadId: number): Observable<any> {
    return this.http.get(`http://localhost:8080/api/nastavnik/zavrsni/${zavrsniRadId}/download`, {
      responseType: 'blob',
      observe: 'response'
    });
  }
}
