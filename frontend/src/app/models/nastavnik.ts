import { Angazovanje } from "./angazovanje";
import { Osoba } from "./osoba";

export interface Nastavnik{
  id:number,
  ime:string,
  prezime:string,
  biografija:string,
  satus:string,
  angazovanja:Angazovanje[],
  //opis:string,
}
