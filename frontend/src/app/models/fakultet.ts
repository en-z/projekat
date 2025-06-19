import { Adresa } from "./adresa";
import { Nastavnik } from "./nastavnik";
import { Univerzitet } from "./univerzitet";
export interface FakultetDTO{
  id:number|null,
  naziv:string,
  opis:string,
  email:string
  kontakt:string,
  adresa:Adresa,
  rektor:Nastavnik,
  univerzitetId:number,
}
