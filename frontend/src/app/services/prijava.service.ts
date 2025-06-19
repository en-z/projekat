import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { Predmet } from "../models/predmet";
import { PrijavaIspit } from "../models/prijavaIspit";

@Injectable({
  providedIn:'root'
})
export class PrijavaService extends BaseService<PrijavaIspit>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/student/prijave-ispita')
  }
}
