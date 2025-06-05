import { Adresa } from "./adresa";

export interface Profil{
  email:string;
  password:string;
  ime:string,
  prezime:string;
  adresa:Adresa;
  status:string;
  biografija:string;
}
