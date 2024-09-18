package au.edu.rmit.sept.webapp.repositories;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import au.edu.rmit.sept.webapp.models.User;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;
import java.sql.Statement;



@Repository
public class UserRepositoryImpl implements UserRepository {

    private final DataSource source;
    public UserRepositoryImpl(DataSource source) {
        this.source = source;
    }

    @Override
    public User insertUserData(User u){
        try(
                Connection connection = this.source.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO user (name, username, email, password) VALUES (?, ?, ?, ?)")){

            ps.setString(1, u.getName());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account successfully created!");
                return u;
            }
        }catch(SQLException e){
            throw new UncategorizedScriptException("Failed to insert data an user in the database", e);
        }

        return u;
    }


    @Override
    public User findByEmail(String email, String password){

        try(
                Connection connection = this.source.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?")){

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Logged in successfully!");
                return  new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
            }
        }catch(SQLException e){
            throw new UncategorizedScriptException("Failed to find an user in the database", e);
        }

        return null;
    }


}