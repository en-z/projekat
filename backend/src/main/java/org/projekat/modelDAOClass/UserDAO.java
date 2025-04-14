package org.projekat.modelDAOClass;

import org.projekat.macros.DB_Macro;
import org.projekat.model.User;
import org.projekat.modelDAOInterface.UserDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements UserDAOInterface {
    static Connection conn = DB_Macro.getConnection();
    @Override
    public int add(User user) throws SQLException {
        String query = "insert into user(email, "+ "password) values (?,?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,user.getEmail());
        ps.setString(2,user.getPassword());
        return ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "delete from user where id =?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    @Override
    public User getUser(int id) throws SQLException {
        String query = "select * from user where id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,id);
        User user = new User();
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next()){
            check=true;
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setId(rs.getInt("id"));
        }
        if(check){
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        String query = "select * from user";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()){
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setId(rs.getInt("id"));
            list.add(user);
        }
        return list;
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "update user set email =?, "+"password=? where id=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1,user.getEmail());
        ps.setString(2,user.getPassword());
        ps.setInt(3,user.getId());
        ps.executeUpdate();
    }
}
