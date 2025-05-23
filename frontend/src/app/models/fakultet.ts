import { Adresa } from "./adresa";
import { Nastavnik } from "./nastavnik";
import { Univerzitet } from "./univerzitet";
export interface FakultetDTO{
  id:number|null,
  naziv:string,
  opis:string,
  kontakt:string,
  adresa:Adresa,
  dekan:Nastavnik,
  univerzitet:Univerzitet,
}
