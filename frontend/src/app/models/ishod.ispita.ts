export interface IshodIspita {
  id: number|null;
  brojPokusaja: number|null;
  rokId:number;
  kolokvijumiId:number[];
  polozen:boolean;
  bodovi: number;
  ocena: number;
  datumUnosa:string| Date;
  studentId: number;
  predmetId: number;
  nastavnikId: number|null;
  instumentId:number;
}
