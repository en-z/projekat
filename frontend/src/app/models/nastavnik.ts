import { Adresa } from "./adresa";
import { Angazovanje } from "./angazovanje";
import { Osoba } from "./osoba";

export interface Nastavnik{
  id:number,
  ime:string,
  prezime:string,
  biografija:string,
  status:string,
  adresa:Adresa,
  angazovanja:Angazovanje[],
  //opis:string,
}
