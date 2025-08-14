export interface ZahtjevSearch {
  userIdMin?: number|null;
  userIdMax?: number|null;
  imeContains?: string|null;
  prezimeContains?: string|null;

  knjigaNazivContains?: string|null;
  knjigaKategorijaContains?: string|null;
  knjigaOpisContains?: string|null;
  knjigaGodinaIzdavanjaMin?: number|null;
  knjigaGodinaIzdavanjaMax?: number|null;
  knjigaAutorContains?: string|null;
  knjigaKolicinaMin?: number|null;
  knjigaKolicinaMax?: number|null;
}

