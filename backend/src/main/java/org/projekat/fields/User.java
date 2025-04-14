package org.projekat.fields;

import org.projekat.macros.DB_Macro;

import java.sql.SQLException;
import org.projekat.macros.DB_Macro.*;
public class User {
    private String email;
    private String password;

    public User(String email,String password){
        this.email = email;
        this.password = password;
    }
    public boolean isRegistered(String email,String password){ //vraca true ako su hash i email tacni ARGON2 kao hash
        String sql = "SELECT 1 FROM \"user\" WHERE email = ? AND password =?";
        return DB_Macro.Query(sql,new Object[]{email,password}, rs->{
            try{
                return rs.next();
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        });
    }

    public boolean Register(String email,String password){
        String sql ="INSERT INTO \"user\" (email,password) VALUES(?,?)";
        DB_Macro.insert(sql,new Object[]{email,password});
        return true;
    }

}
