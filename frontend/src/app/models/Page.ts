export interface Page<T>{
  content: T[];
  totalElements: number;
  totalPages: number;
  number: number;         // trenutna stranica
  size: number;           // veličina stranice
  first: boolean;
  last: boolean;
}
