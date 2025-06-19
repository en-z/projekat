import { jsPDF } from 'jspdf';
import {autoTable} from 'jspdf-autotable';

function vToString(v: any): string {
  if (v == null) return '';
  if (v instanceof Date) return v.toLocaleDateString();

  if (typeof v === 'object') {
    if (v.toString && v.toString !== Object.prototype.toString) {
      return v.toString();
    }
    if ('ulica' in v && 'broj' in v && 'grad' in v && 'drzava' in v) {
      return `${v.ulica} ${v.broj}, ${v.grad}, ${v.drzava}`;
    }
    try {
      return JSON.stringify(v);
    } catch {
      return "Unstringifiable Object";
    }
  }
  return String(v);
  }
export function exportToPdf<T extends Record<string, any>>(
  data: T[],
  fn: string,
  title: string,
) {
  if (data.length === 0) {
    return;
  }

  const doc = new jsPDF();
  doc.setFontSize(15);
  doc.text(title, 14, 22);

  const columns = Object.keys(data[0]);
  const rows = data.map((row) => columns.map((col) => vToString(row[col])));

  autoTable(doc, {
    startY: 30,
    head: [columns],
    body: rows,
  });

  doc.save(fn);
}
