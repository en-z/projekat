package org.projekat.statics;

public class Wayne {
    public static boolean isSameId(long id,Long idFromReq){
        if(idFromReq==null){
            return false;
        }
        return id == idFromReq;
    }
    public static boolean isEven(long num){
        return (num&1)==0;
    }
}
