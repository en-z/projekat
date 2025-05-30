import { Adresa } from "./adresa"
import { FakultetDTO } from "./fakultet"
import { Nastavnik } from "./nastavnik"
export interface Univerzitet{
  id?:number,
  naziv:string,
  kontakt:string,
  email:string,
  opis:string,
  rektor:Nastavnik,
  adresa:Adresa,
}
