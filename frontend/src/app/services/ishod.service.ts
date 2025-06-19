import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { IshodIspita } from "../models/ishodIspita";

@Injectable({
  providedIn:'root'
})
export class IshodService extends BaseService<IshodIspita>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/api/nastavnik/ishodi')
  }

}

