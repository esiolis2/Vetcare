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
import java.time.LocalDate;
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


//package au.edu.rmit.sept.webapp.repositories;
//
//import au.edu.rmit.sept.webapp.models.PetInformation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PetInformationRepositoryImpl implements PetInformationRepository {
//
//    private final DataSource dataSource;
//
//    // SQL Queries based on the PET_INFO table
//    private static final String FIND_ALL_QUERY = "SELECT * FROM PET_INFO";
//    private static final String FIND_BY_ID_QUERY = "SELECT * FROM PET_INFO WHERE PetID = ?";
//
//    @Autowired
//    public PetInformationRepositoryImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public List<PetInformation> findAll() throws SQLException {
//        List<PetInformation> PET_INFO = new ArrayList<>();
//
//        try (Connection connection = this.dataSource.getConnection();
//             PreparedStatement stm = connection.prepareStatement(FIND_ALL_QUERY);
//             ResultSet rs = stm.executeQuery()) {
//
//            while (rs.next()) {
//                PetInformation petInformation = new PetInformation(
//                        rs.getLong("PetID"),
//                        rs.getString("Name"),
//                        rs.getInt("Age"),
//                        rs.getString("Gender"),
//                        rs.getDouble("Weight"),
//                        rs.getString("Breed"),
//                        rs.getDate("BirthDate").toLocalDate(),  // LocalDate conversion
//                        rs.getString("OwnerName"),
//                        rs.getString("OwnerContact")
//                );
//                PET_INFO.add(petInformation);
//            }
//
//        } catch (SQLException e) {
//            throw new SQLException("Error in fetching all pets", e);
//        }
//
//        return PET_INFO;
//    }
//
//    @Override
//    public PetInformation findPetById(Long id) throws SQLException {
//        try (Connection connection = this.dataSource.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(FIND_BY_ID_QUERY)) {
//
//            stmt.setLong(1, id);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return new PetInformation(
//                            rs.getLong("PetID"),
//                            rs.getString("Name"),
//                            rs.getInt("Age"),
//                            rs.getString("Gender"),
//                            rs.getDouble("Weight"),
//                            rs.getString("Breed"),
//                            rs.getDate("BirthDate").toLocalDate(),  // LocalDate conversion
//                            rs.getString("OwnerName"),
//                            rs.getString("OwnerContact")
//                    );
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new SQLException("Error in fetching pet by ID", e);
//        }
//
//        return null;
//    }
//}





















//package au.edu.rmit.sept.webapp.repositories;
//
//import au.edu.rmit.sept.webapp.models.PetInformation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.UncategorizedSQLException;
//
////import org.springframework.jdbc.datasource.init.UncategorizedScriptException;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class PetInformationRepositoryImpl implements PetInformationRepository{
//
//    @Autowired
//    private final DataSource dataSource;
//
//
//    public PetInformationRepositoryImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//
//    @Override
//    public List<PetInformation> findAll() {
//        String query = "SELECT * FROM PetInformation";
//        try{
//            Connection connection = this.dataSource.getConnection();
//            PreparedStatement stm = connection.prepareStatement("SELECT * FROM PET_INFO;");
//            ResultSet rs = stm.executeQuery();
//            List<PetInformation> petInformations = new ArrayList<>();
//
//            while (rs.next()) {
//                PetInformation petInformation = new PetInformation(
//                        rs.getLong("PetID"),
//                        rs.getString("Name"),
//                        rs.getInt("Age"),
//                        rs.getString("Gender"),
//                        rs.getDouble("Weight"),
//                        rs.getString("Breed"),
//                        rs.getString("BirthDate"),
//                        rs.getString("OwnerName"),
//                        rs.getString("OwnerContact")
//                );
//                petInformations.add(petInformation);
//            }
//
//            return petInformations;
//        } catch (SQLException e) {
//            throw new UncategorizedSQLException("Error in fetching all pets", query, e);
//        }
//    }
//
//    @Override
//    public PetInformation findPetById(Long id) {
//        String query = "SELECT * FROM PetInformation WHERE PetID = ?";
//        try {
//            Connection connection = this.dataSource.getConnection();
//            PreparedStatement stmt = connection.prepareStatement(query);
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return new PetInformation(
//                        rs.getLong("PetID"),
//                        rs.getString("Name"),
//                        rs.getInt("Age"),
//                        rs.getString("Gender"),
//                        rs.getDouble("Weight"),
//                        rs.getString("Breed"),
//                        rs.getString("BirthDate"),
//                        rs.getString("OwnerName"),
//                        rs.getString("OwnerContact")
//                );
//            }
//        } catch (SQLException e) {
//            throw new UncategorizedSQLException("Error in fetching pet by ID", query, e);
//        }
//        return null;
//    }
//}














//    @Override
//    public List<PetInformation> findAll() {
//        try (Connection connection = this.dataSource.getConnection();
//             PreparedStatement stm = connection.prepareStatement("SELECT * FROM PetInformation");
//             ResultSet rs = stm.executeQuery()) {
//
//            List<PetInformation> pets = new ArrayList<>();
//            while (rs.next()) {
//                PetInformation pet = new PetInformation(
//                        rs.getLong("PetID"),
//                        rs.getString("Name"),
//                        rs.getInt("Age"),
//                        rs.getString("Gender"),
//                        rs.getDouble("Weight"),
//                        rs.getString("Breed"),
//                        rs.getDate("BirthDate") != null ? String.valueOf(rs.getDate("BirthDate").toLocalDate()) : null,
//                        rs.getString("OwnerName"),
//                        rs.getString("OwnerContact")
//                );
//                pets.add(pet);
//            }
//            return pets;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error fetching pets", e);
//        }
//    }
//
//    @Override
//    public PetInformation findPetById(Long id) {
//        String query = "SELECT * FROM PetInformation WHERE PetID = ?";
//        try (Connection connection = this.dataSource.getConnection();
//             PreparedStatement stmt = connection.prepareStatement(query)) {
//
//            stmt.setLong(1, id);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return new PetInformation(
//                        rs.getLong("PetID"),
//                        rs.getString("Name"),
//                        rs.getInt("Age"),
//                        rs.getString("Gender"),
//                        rs.getDouble("Weight"),
//                        rs.getString("Breed"),
//                        rs.getDate("BirthDate") != null ? String.valueOf(rs.getDate("BirthDate").toLocalDate()) : null,
//                        rs.getString("OwnerName"),
//                        rs.getString("OwnerContact")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error fetching pet by id", e);
//        }
//        return null;
//    }
//}
