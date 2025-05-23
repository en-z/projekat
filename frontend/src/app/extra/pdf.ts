import jspdf from 'jspdf';
import "jspdf-autotable";

function vToString(v:any):string|{error:string}{
  if(v==null)return '';
  if(v instanceof Date) return v.toLocaleDateString()

  if(typeof v ==='object'){
    if(v.toString && v.toString !== Object.prototype.toString){
      return v.toString();
    }
    try{
      return JSON.stringify(v);
    }catch{
      return {error:"Error: Json nece da stringify value"}
    }
  }
  return String(v)
}
export function exportToPdf<T extends Record<string,any>>(
  data:T[],
  fn:string,
  title:string,
){
  if(data.length ===0){
      return ;
  }
  const doc = new jspdf();
  doc.setFontSize(15);
  doc.text(title,14,22);
  const columns = Object.keys(data[0]);
  const rows = data.map((r)=>{
    columns.map((c)=>vToString(r[c]))
  });
  (doc as any).autoTable({
    startY:30,
    head:[columns],
    body:rows,
  });
  doc.save(fn)
}
