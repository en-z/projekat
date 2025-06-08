import {create} from 'xmlbuilder2'
import {writeFileSync}from 'fs'

function addObjectToXml(node:any,obj:Record<string,any>):void{ //en:testriaj rekurziju
  for(const[k,v]of Object.entries(obj)){
    if(Array.isArray(v)){
      v.forEach(i=>{
        const child =node.ele(k);
        if(typeof i ==='object' && i !==null){
          addObjectToXml(child,i);
        }else{
          child.txt(String(i));
        }
        child.up();
      });
    }else if(typeof v ==='object' && v!==null){
      const child = node.ele(k);
      addObjectToXml(child,v);
      child.up();
    }else{
      node.ele(k).txt(String(v)).up();
    }
  }
}

export function exportToXml<T extends Record<string,any>>(
  data:T[],
  root:string,
  item:string,
  filename:string,
):void{
  const doc = create({version:'1.0'}).ele(root)
  data.forEach(i=>{
    const node=doc.ele(item)
    addObjectToXml(node,i);
    node.up();
  });
  const xml=doc.end({prettyPrint:true});
  const blob = new Blob([xml], { type: 'application/xml' });
  const link = document.createElement('a');
  link.href = URL.createObjectURL(blob);
  link.download = filename;
  link.click();
  URL.revokeObjectURL(link.href);
}
