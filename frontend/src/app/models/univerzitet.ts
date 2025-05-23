import { Adresa } from "./adresa"
import { FakultetDTO } from "./fakultet"
import { Nastavnik } from "./nastavnik"
export interface Univerzitet{
  id:number|null,
  naziv:string,
  kontakt:string,
  opis:string,
  rektor:Nastavnik,
  adresa:Adresa,
}
