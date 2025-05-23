import { Nastavnik } from "./nastavnik";
import { Predmet } from "./predmet";

export interface IshodIspita{
  id:number;
  brojPokusaja:number;
  bodovi:number;
  predmet:Predmet;
  nastavnik:Nastavnik;
  //instrumetEvaluacije:InstrumentEvaluacije;
}
