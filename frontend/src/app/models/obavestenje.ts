export interface Obavestenje {
  id: number;
  naslov: string;
  tekst: string;
  datumPostavljanja: Date | null;
  predmetId: number;
  nastavnikId: number;
  ime:string,
  prezime:string,
}
