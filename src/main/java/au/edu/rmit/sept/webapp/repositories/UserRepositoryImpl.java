package au.edu.rmit.sept.webapp.repositories;


import au.edu.rmit.sept.webapp.models.User;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
    public User findByEmail(String email){
        try(
                Connection connection = this.source.getConnection();
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM user WHERE email = ?")){

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("Logged in successfully!");
                return  new User(rs.getLong("id"), rs.getString("name"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
            }
        }catch(SQLException e){
            throw new UncategorizedScriptException("Failed to find an user in the database", e);
        }

        return null;
    }


//    @Override
//    public List<User> findAll() {
//        List<User> users = new ArrayList<>();
//        try (Connection connection = this.source.getConnection();
//             PreparedStatement ps = connection.prepareStatement("SELECT * FROM user");
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("email"), rs.getString("password"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            throw new UncategorizedScriptException("Failed to retrieve users from the database", e);
//        }
//        return users;
//    }


}