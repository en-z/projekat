import { Knjiga } from "./knjiga";
import { Osoba } from "./osoba";

export interface Izdate{
  id?:number
  knjigaId: number;
  userId: number;
  ime: string;
  prezime: string;
  trajan: boolean;
  datumVracanja?: string | null; // ISO format datuma ili null
}
