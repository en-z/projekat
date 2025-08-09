import { Injectable } from "@angular/core";
import { Observable, retry } from "rxjs";
import { Inventar } from "../models/inventar";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class InventarService{
  private baseUrl = 'http://localhost:8080/api/osoblje/inventar';

  constructor(private http: HttpClient) {}

  getAll(fakultetId: number): Observable<Inventar[]> {
    return this.http.get<Inventar[]>(`${this.baseUrl}/${fakultetId}`);
  }

  create(inventar: Inventar): Observable<Inventar> {
    return this.http.post<Inventar>(this.baseUrl, inventar);
  }

  saveAll(inventarList: Inventar[]): Observable<Inventar[]> {
    const fakultetId = inventarList[0]?.fakultetId;
    return this.http.put<Inventar[]>(`${this.baseUrl}/${fakultetId}/batch`, inventarList);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
