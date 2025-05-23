import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { StudiskiProgram } from "../models/StudiskiProgram";

@Injectable({
  providedIn:'root'
})
export class StudiskiService extends BaseService<StudiskiProgram>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/auth/admin/predmet')
  }
}

