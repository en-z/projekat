package org.projekat.macros;

import org.mindrot.jbcrypt.BCrypt;

import java.security.SecureRandom;

public class PasswordHashing {

    public static String genSalt(){
        return BCrypt.gensalt();
    }
    public static String genPasswordHash(String password,String salt){
        return BCrypt.hashpw(password,salt);
    }

}
