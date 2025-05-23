import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { IshodIspita } from "../models/ishodIspita";
import { FakultetDTO } from "../models/fakultet";
import { Univerzitet } from "../models/univerzitet";

@Injectable({
  providedIn:'root'
})
export class UniverzitetService extends BaseService<Univerzitet>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/auth/admin/univerzitet')
  }
}

