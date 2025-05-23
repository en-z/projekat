import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { BaseService } from "./base.service";
import { User } from "../models/user";

@Injectable({
  providedIn:'root'
})
export class UserService extends BaseService<User>{
  constructor(http:HttpClient){
    super(http,'http://localhost:8080/auth/admin/sifarnik')
  }
  getByEmail(email:string){
    const params = new HttpParams().set('email',email)
    return this.http.get<User>(this.baseUrl,{params}) ;
  }
}

