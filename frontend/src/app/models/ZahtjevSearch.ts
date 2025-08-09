export interface ZahtjevSearch {
  userIdMin?: number;
  userIdMax?: number;
  imeContains?: string;
  prezimeContains?: string;

  knjigaNazivContains?: string;
  knjigaKategorijaContains?: string;
  knjigaOpisContains?: string;
  knjigaGodinaIzdavanjaMin?: number;
  knjigaGodinaIzdavanjaMax?: number;
  knjigaAutorContains?: string;
  knjigaKolicinaMin?: number;
  knjigaKolicinaMax?: number;
}

