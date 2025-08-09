import { Knjiga } from "./knjiga";

export interface Zahtjev{
  id:number;
  userId:number;
  ime:string,
  prezime:string,
  knjiga:Knjiga
}
