import {Injectable} from '@angular/core'
import { Router} from '@angular/router'
@Injectable({
  providedIn:'root'
})

export class AuthService{
  roles: string[] = [];
  currentUserId: number | undefined;

  constructor(private router:Router){}
  logout(){
    localStorage.removeItem('jwtToken')
    this.roles = [];
    this.router.navigate(['/login'])
  }
  isLoggedIn():boolean{
    return !!localStorage.getItem('jwtToken')
  }
  loadRoleFromJWt(){
    const token = localStorage.getItem('jwtToken')
    if(!token)return;

    try{
      const payload = JSON.parse(atob(token.split('.')[1]))
      this.roles = payload.roles || []
      console.table(payload);
      console.log(payload);
      this.currentUserId = payload.id
    }catch(err){
      console.error("jwt:",err)
      this.roles = []
      this.currentUserId = undefined
    }
  }
  isExpired():boolean{
    const token = localStorage.getItem('jwtToken')
    if(!token)return true;
    try{
      const payload = JSON.parse(atob(token.split('.')[1]));
      const exp = payload.exp
      const now = Math.floor(Date.now()/1000)
      return now>exp
    }catch (e){
      return true;
    }
  }
  hasRole(role:string):boolean{
    return this.roles.includes(role);
  }
  getAllRoles():string[]{
    return this.roles;
  }
}
