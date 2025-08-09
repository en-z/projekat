import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DokumentRequest } from '../models/dokument';

@Injectable({
  providedIn: 'root'
})
export class DokumentService {
  private baseUrl = 'http://localhost:8080/api/osoblje/dokument';

  constructor(private http: HttpClient) {}

  uploadDokument(request: DokumentRequest): Observable<any> {
    const formData = new FormData();
    formData.append('file', request.file);
    formData.append('naslov', request.naslov);
    formData.append('opis', request.opis);
    formData.append('userId',request.userId.toString());
    return this.http.post(`${this.baseUrl}/upload`, formData);
  }
}

