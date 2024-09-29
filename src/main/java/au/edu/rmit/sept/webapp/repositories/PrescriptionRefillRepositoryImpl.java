package au.edu.rmit.sept.webapp.repositories;

import au.edu.rmit.sept.webapp.models.PrescriptionRefillRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class PrescriptionRefillRepositoryImpl implements PrescriptionRefillRepository {

    private final DataSource dataSource;

    @Autowired
    public PrescriptionRefillRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveRefillRequest(PrescriptionRefillRequest refillRequest) {
        String query = "INSERT INTO PrescriptionRefillRequests (PetID, Medications, Address, City, State, Postcode, Notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setLong(1, refillRequest.getPetID());
            stmt.setString(2, refillRequest.getMedications());
            stmt.setString(3, refillRequest.getAddress());
            stmt.setString(4, refillRequest.getCity());
            stmt.setString(5, refillRequest.getState());
            stmt.setString(6, refillRequest.getPostcode());
            stmt.setString(7, refillRequest.getNotes());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving prescription refill request", e);
        }
    }
}
