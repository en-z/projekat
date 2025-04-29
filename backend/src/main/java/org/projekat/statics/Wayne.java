package org.projekat.statics;

public class Wayne {
    public static boolean isSameId(long id,Long idFromReq){
        if(idFromReq==null){
            return false;
        }
        return id == idFromReq;
    }
}
