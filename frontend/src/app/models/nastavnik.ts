import { Angazovanje } from "./angazovanje";

export interface Nastavnik {
  osobaId: number;
  status: string;
  angazovanja: Angazovanje[];
}