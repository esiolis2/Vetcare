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

    public User insertUserData(User u){
      try(
          Connection connection = this.source.getConnection();
          PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name, username, email, password) VALUES (?, ?, ?, ?);")){

          ps.setString(1, u.getName());
          ps.setString(2, u.getUsername());
          ps.setString(3, u.getEmail());
          ps.setString(4, u.getPassword());

          int rowsAffected = ps.executeUpdate();
          if (rowsAffected > 0) {
              // the generated user ID
              ResultSet generatedKeys = ps.getGeneratedKeys();
              if (generatedKeys.next()) {
                  u.setId(generatedKeys.getInt(1));
              }

              System.out.println("Account successfully created!");
              return u;
          }
      }catch(SQLException e){
          throw new UncategorizedScriptException("Failed to insert data an user in the database", e);
      }

      return u;
    }


}
