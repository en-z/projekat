package org.projekat.macros;

import java.sql.*;
import java.util.Objects;
import java.util.function.Function;

public class DB_Macro {
        static final String URL = "jdbc:postgresql://localhost:8080/baza";
        static final String User = "username";//Zamjeni
        static final String Password = "password";//Zamjeni

        public static <T>T Query(String sqlString, Object[] params, Function<ResultSet,T>handler){
        assert !User.equals("username") : "Username je username zamjeni u queryMacro fajl ";// tu je samo za pocetak da panic ako nije promjenjeno
        try (
                Connection conn = DriverManager.getConnection(URL, User, Password);
                PreparedStatement stmt = conn.prepareStatement(sqlString)
        ) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                return handler.apply(rs);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void insert(String sqlQuery,Object[] params){ //TODO(en):odliciti da mozda vrace bool? jer mislim da ce biti bolje
        try(
                Connection conn = DriverManager.getConnection(URL,User,Password);
                PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        ){
            for(int i = 0;i< params.length;i++){
                stmt.setObject(i+1,params[i]);
            }
            stmt.executeUpdate();
        }catch (Exception e){ //TODO(en):doredi ovaj error kasnije
            throw new RuntimeException(e);
        }
    }
}
