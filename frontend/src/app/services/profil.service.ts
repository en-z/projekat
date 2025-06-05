import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Profil } from "../models/profil.service";

@Injectable({
  providedIn:'root'
})
export class ProfilService extends BaseService<Profil>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/profil')
  }
  getProfil(){
    return this.http.get<Profil>(this.baseUrl)
  }
  postProfil(profil:Profil){
    return this.http.post<Profil>(this.baseUrl,profil)
  }

}

