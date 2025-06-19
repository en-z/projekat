import { Knjiga } from "./knjiga";
import { Osoba } from "./osoba";

export interface Izdate{
  id?:number,
  knjiga:Knjiga,
  userId:number;
  datumIzdavanja:Date,
  datumVracanja:Date|null,
}
