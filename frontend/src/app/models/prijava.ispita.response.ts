import { Student } from "./student";

export interface PrijavaIspitaResponse {
  id: number;
  godina: number;
  rok: string;
  datumPrijave: Date;
  predmetId: number;
  student: Student;
}