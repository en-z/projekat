import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Predmet } from "../models/predmet";

@Injectable({
  providedIn:'root'
})
export class PrijavaService extends BaseService<Predmet>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/auth/student/prijava')
  }
}
