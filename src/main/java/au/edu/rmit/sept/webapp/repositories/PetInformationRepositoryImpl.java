package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PetInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PetInformationRepositoryImpl implements PetInformationRepository {

    @Autowired
    private final DataSource dataSource;

    public PetInformationRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<PetInformation> findAll() {
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM PET_INFO;");
            List<PetInformation> PET_INFO = new ArrayList<>();
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PetInformation petInformation = new PetInformation(
                        rs.getLong("PetID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender"),
                        rs.getDouble("Weight"),
                        rs.getString("Breed"),
                        rs.getDate("BirthDate").toLocalDate(),
                        rs.getString("OwnerName"),
                        rs.getString("OwnerContact")
                );
                PET_INFO.add(petInformation);
            }
            connection.close();
            return PET_INFO;
        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error in findAll", e);
        }
    }

    @Override
    public PetInformation addPetInformation(PetInformation petInformation) {
        String insertQuery = "INSERT INTO PET_INFO (Name, Age, Gender, Weight, Breed, BirthDate, OwnerName, OwnerContact) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(insertQuery)) {

            stmt.setString(1, petInformation.getName());
            stmt.setInt(2, petInformation.getAge());
            stmt.setString(3, petInformation.getGender());
            stmt.setDouble(4, petInformation.getWeight());
            stmt.setString(5, petInformation.getBreed());
            stmt.setDate(6, java.sql.Date.valueOf(petInformation.getBirthDate()));
            stmt.setString(7, petInformation.getOwnerName());
            stmt.setString(8, petInformation.getOwnerContact());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error inserting pet information", e);
        }

        return petInformation;
    }

    @Override
    public PetInformation findPetById(Long id) {
        String query = "SELECT * FROM PET_INFO WHERE PetID = ?";
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new PetInformation(
                        rs.getLong("PetID"),
                        rs.getString("Name"),
                        rs.getInt("Age"),
                        rs.getString("Gender"),
                        rs.getDouble("Weight"),
                        rs.getString("Breed"),
                        rs.getDate("BirthDate").toLocalDate(),
                        rs.getString("OwnerName"),
                        rs.getString("OwnerContact")
                );
            }

        } catch (SQLException e) {
            throw new UncategorizedScriptException("Error fetching pet by ID", e);
        }

        return null;
    }
}
