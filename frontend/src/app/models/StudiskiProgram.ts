import { FakultetDTO } from "./fakultet";
import { Nastavnik } from "./nastavnik";

export interface StudiskiProgram{
  id:number|null,
  naziv:string,
  opis:string,
  rukovodioc:Nastavnik,
  fakultetId:number,
}
