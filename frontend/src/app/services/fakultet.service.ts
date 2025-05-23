import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { IshodIspita } from "../models/ishodIspita";
import { FakultetDTO } from "../models/fakultet";

@Injectable({
  providedIn:'root'
})
export class FakultetService extends BaseService<FakultetDTO>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/auth/admin/fakultet')
  }

}
