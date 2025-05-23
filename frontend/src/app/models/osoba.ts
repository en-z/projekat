import { Adresa } from "./adresa";
export interface Osoba {
  user_id:number;
  ime:string;
  prezime:string;
  email:string;
  adresa?:Adresa;
}
