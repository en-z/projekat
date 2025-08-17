import { Predmet } from "./predmet";

export interface Angazovanje {
  id: number;
  predmetId: number;
  nastavnikId: number;
  uloga: string;
}
