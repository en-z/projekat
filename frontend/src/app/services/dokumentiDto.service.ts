import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseService } from './base.service';

@Injectable({
  providedIn: 'root'
})
export class DokumentDtoService {
   private baseUrl = 'http://localhost:8080/api/osoblje/dokument';

  constructor(private http: HttpClient) {}

  getDokumenti(page: number, size: number, sortBy = 'datum', direction = 'desc'): Observable<any> {
    let params = new HttpParams()
      .set('page', page)
      .set('size', size)
      .set('sortBy', sortBy)
      .set('direction', direction);
    return this.http.get<any>(`${this.baseUrl}/dokument`, { params });
  }

  getDokumentiByUserId(userId: number, page: number, size: number): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size);
    return this.http.get<any>(`${this.baseUrl}/user/${userId}`, { params });
  }
}
