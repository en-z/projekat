import { Knjiga } from "./knjiga";
import { Osoba } from "./osoba";

export interface Izdate{
  id?:number,
  knjiga:Knjiga,
  osoba:Osoba,
  datumIzdavanja:Date,
  datumVracanja?:Date,
}
