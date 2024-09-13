package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.Veterinarian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VeterinarianRepositoryImpl implements VeterinarianRepository{

    @Autowired
    private final DataSource dataSource;

    public VeterinarianRepositoryImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public List<Veterinarian> findAllVeterinarians() {
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm= connection.prepareStatement("SELECT * FROM VETERINARIAN");
            ResultSet rs = stm.executeQuery();
            List<Veterinarian>veterinarians = new ArrayList<>();
            while(rs.next()){
                Veterinarian veterinarian = new Veterinarian(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getLong("clinicId")
                );
                veterinarians.add(veterinarian);
            }
            return veterinarians;
        }
        catch (SQLException e){
            throw new IllegalArgumentException("Cannot get all veterinarians", e);
        }
    }

}
